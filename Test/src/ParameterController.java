package api.atmax.controllers.incentmax;

import static api.atmax.util.validator.ValidatorUtil.isNull;
import static org.mockito.ArgumentMatchers.charThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.atmax.controllers.GenericController;
import api.atmax.controllers.incentmax.utils.ParameterValidatorUtil;
import api.atmax.custom.messages.AccessDenied;
import api.atmax.custom.messages.DuplicateKey;
import api.atmax.custom.messages.Required;
import api.atmax.custom.messages.RestException;
import api.atmax.models.Incentive;
import api.atmax.models.Parameter;
import api.atmax.models.refrence.IncentiveParameter;
import api.atmax.models.refrence.IncentiveSlab;
import api.atmax.repositories.incentmax.IncentiveParameterRepository;
import api.atmax.repositories.incentmax.IncentiveRepository;
import api.atmax.repositories.incentmax.ParameterRepository;
import api.atmax.services.incentive.IncentiveService;
import api.atmax.services.interfaces.AtmaxConfigService;
import api.atmax.services.interfaces.ParameterService;
import api.atmax.util.validator.ValidatorUtil;
import api.atmax.utils.Constants;
import api.atmax.utils.CustomMethods;
import api.atmax.utils.EnumFields;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RequestMapping(value = "/api/parameter", produces = "application/hal+json")
@RestController
@Slf4j
public class ParameterController extends GenericController<Parameter> {

	@Autowired
	private ParameterRepository parameterRepository;
	@Autowired
	private AtmaxConfigService atmaxConfigService;
	@Autowired
	private IncentiveParameterRepository incentiveParameter;
	@Autowired
	private IncentiveRepository incentiveRepository;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private IncentiveService incentiveService;

	private final static String REQUIRED_NAME = "Parameter Name is required";
	private final static String TARGET_OR_ACHIEVEMENT_REQUIRED_NAME = "At least target or achievement is required!";
	private final static String MODEL = "Parameter";
	//private final static String NAME_NOT_UPPER_CASE = "name should not be in upper case";
	//private final static String NAME_VALID = "valid name details";
	//private final static String WHITE_SPACE="name Should not contain Space";
	//private final static String IS_DIGIT="name Should not contain digit";
	//private final static String SPCL_CHAR="name should not contain special character";

	/**
	 * @param authentication logged-in user details
	 * @return all data of particular organization
	 */
	@RequestMapping(method = RequestMethod.GET)
	private List<Parameter> read(Authentication authentication) {
		if (!aclService.readPermission(authentication, Constants.PARAMETER))
			throw new AccessDenied(successMessage.aclReadPermission() + authentication.getName());

		Query query = getOrganizationQuery(authentication);
		query.fields().exclude("orgId").exclude("update_trackers");
		query.with(new Sort(Sort.Direction.DESC, Constants.CREATEED_DATE));
		return mongoTemplate.find(query, Parameter.class);

	}

	/**
	 * @param entity         object that needs to be create
	 * @param authentication logged in user org id
	 * @return create data
	 */
	@Override
	public Parameter create(@RequestBody Parameter entity, Authentication authentication) {

		if (!aclService.createPermission(authentication, Constants.PARAMETER))
			throw new AccessDenied(successMessage.aclCreatePermission() + authentication.getName());
		String name = (entity.getName() != null) ? (entity.getName()) : "";

		if (name.isEmpty())
			throw new Required(REQUIRED_NAME);// Parameter Name is required
		else
			ParameterValidatorUtil.validateNameOrThrow(name);
		
		
		String orgId = userService.loggedInUserOrganisationId(authentication);
		final Parameter byNameAndOrgId;
		byNameAndOrgId = parameterService.getParameterByNameAndOrgId(name, orgId);
		Incentive incentive = incentiveService.getIncentiveByNameAndOrgId(name, orgId);
//        		parameterRepository.findByNameAndOrgId(name,
//                userService.loggedInUserOrganisationId(authentication));

		if (byNameAndOrgId != null)
			throw new DuplicateKey(StringUtils.capitalize(entity.getName()) + " Parameter Already existed");

		if (Objects.nonNull(incentive))
			throw new DuplicateKey(StringUtils.capitalize(entity.getName()) + " Incentive already existed");

		if (!entity.isQualifier() && isParameterTargetAndAchiEnabled(entity)) {
			throw new Required(TARGET_OR_ACHIEVEMENT_REQUIRED_NAME);
		}

		setAchievementAndTargetFalseIfNull(entity);

		double end = 0;
		if (entity.getParameter_slabs() != null)
			for (IncentiveSlab parameter_slab : entity.getParameter_slabs()) {
				if ((end != 0) && (end != parameter_slab.getStart())) {
					throw new RestException("Parameter slab setup is not correct for start slab '"
							+ parameter_slab.getStart() + "' it should be start with '" + end + "'");
				}
				end = parameter_slab.getEnd();
			}

//        final Parameter byNameAndOrgId;
//        byNameAndOrgId = parameterRepository.findByNameAndOrgId(name,
//                userService.loggedInUserOrganisationId(authentication));
//
//        if (byNameAndOrgId != null)
//            throw new DuplicateKey(StringUtils.capitalize(entity.getName()) + " Already existed");
		return super.create(entity, authentication);
	}

	private boolean isParameterTargetAndAchiEnabled(Parameter entity) {
		return (entity.isTarget() == null || !entity.isTarget())
				&& (entity.isAchievement() == null || !entity.isAchievement());
	}

	/**
	 * @param id             object id
	 * @param entity         object that needs to be update
	 * @param authentication logged in user org id
	 * @return update particular object
	 */
	@Override
	public Parameter update(@PathVariable String id, @RequestBody Parameter entity, Authentication authentication) {
		if (!aclService.updatePermission(authentication, Constants.PARAMETER))
			throw new AccessDenied(successMessage.aclUpdatePermission() + authentication.getName());
		String name = (entity.getName() != null) ? (entity.getName()) : "";
		if (name.isEmpty())
			throw new Required(REQUIRED_NAME);

		if (!entity.isQualifier() && isParameterTargetAndAchiEnabled(entity)) {
			throw new Required(TARGET_OR_ACHIEVEMENT_REQUIRED_NAME);
		}

		double end = 0;
		if (entity.getParameter_slabs() != null)
			for (IncentiveSlab parameter_slab : entity.getParameter_slabs()) {
				if ((end != 0) && (end != parameter_slab.getStart())) {
					throw new RestException("Parameter slab setup is not correct for start slab '"
							+ parameter_slab.getStart() + "' it should be start with '" + end + "'");
				}
				end = parameter_slab.getEnd();
			}

		final Optional<Parameter> byId = repository.findById(id);
		List<String> incentiveNames = findAndAddIncentiveNames(findAndAddIncentiveIds(authentication, byId));

		// if incentive names is not empty
		if (!incentiveNames.isEmpty())
			byId.ifPresent(parameter -> entity.setName(parameter.getName()));
		else
			entity.setName(entity.getName());

		return super.update(id, entity, authentication);
	}

	/**
	 * @return all fields of the Parameter model class
	 */
	@GetMapping(value = "column_name")
	private Map<Object, Object> getModuleFields() {
		return CustomMethods.getModuleFields(MODEL);
	}

	/**
	 * @param id             object id
	 * @param authentication logged in user org id
	 * @return deletes a parameter
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity delete(@PathVariable String id, Authentication authentication) {
		// finding parameter by id
		Optional<Parameter> byId = repository.findById(id);
		List<String> incentiveNames = findAndAddIncentiveNames(findAndAddIncentiveIds(authentication, byId));

		// if incentive names is not empty
		if (!incentiveNames.isEmpty())
			throw new Required(
					"Parameter is linked with different incentive please remove parameter from these incentives "
							+ incentiveNames);
		return super.delete(id, authentication);
	}

	/**
	 * @param authentication org id
	 * @param byId           find parameter name using parameter id
	 * @return List of incentive ids where current param is linked
	 */
	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	private Set<String> findAndAddIncentiveIds(Authentication authentication, Optional<Parameter> byId) {
		// add incentive ids to the list
		Set<String> incentiveIds = new HashSet<>();
		byId.ifPresent(parameter -> {
			String name = parameter.getName();
			List<IncentiveParameter> byOrgId;
			byOrgId = incentiveParameter.findByOrganizationId(userService.loggedInUserOrganisationId(authentication));
			if (byOrgId != null)
				for (IncentiveParameter incentiveParameter : byOrgId) {
					if (incentiveParameter.getParameters().contains(name)) {
						incentiveIds.add(incentiveParameter.getIncentive_id());
					}
				}
		});
		return incentiveIds;
	}

	/**
	 * @param incentiveIds List of incentive ids
	 * @return list of incentive names
	 */
	private List<String> findAndAddIncentiveNames(Set<String> incentiveIds) {
		// add incentive names to the list
		List<String> incentiveNames;
		incentiveNames = new ArrayList<>();
		if (!incentiveIds.isEmpty())
			for (String incentiveId : incentiveIds) {
				Optional<Incentive> byNameAndOrgId = incentiveRepository.findById(incentiveId);
				byNameAndOrgId.ifPresent(incentive -> incentiveNames.add(incentive.getName()));
			}
		return incentiveNames;
	}

	@GetMapping("/list")
	private Object getParametersList(Authentication authentication) {
		log.info("**** getParametersList ****");
		Query query = getOrganizationQuery(authentication);
		query.fields().include(Constants.ID).include(Constants.NAME).include(Constants.ORGANIZATION_ID);
		query.with(new Sort(Sort.Direction.DESC, Constants.CREATEED_DATE));
		List<Parameter> parameters = mongoTemplate.find(query, Parameter.class);

		List<Map<Object, Object>> list = new ArrayList<>();

		for (Parameter parameter : parameters) {
			Map<Object, Object> model = new LinkedHashMap<>();
			model.put(parameter.getId(), parameter.getName());
			list.add(model);
		}
		return list;
	}

	@GetMapping("/type")
	private Object getParameterTypes(Authentication authentication) {

		try {

			if (!aclService.readPermission(authentication, Constants.PARAMETER))
				throw new AccessDenied(successMessage.aclReadPermission() + authentication.getName());

			List<Map<Object, Object>> list = new ArrayList<>();
			for (EnumFields.parameter_type value : EnumFields.parameter_type.values()) {
				Map<Object, Object> model = new LinkedHashMap<>();
				model.put(value.toString().toLowerCase(), StringUtils.capitalize(value.toString().toLowerCase()));
				list.add(model);
			}

			return list;
		} catch (AccessDenied accessDenied) {
			throw new AccessDenied(accessDenied.getMessage());
		} catch (NullPointerException nullPointerException) {
			nullPointerException.printStackTrace();
			throw new NullPointerException(nullPointerException.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RestException(e.getMessage());
		}
	}

	@GetMapping("/paramter_value/type/{parameterType}/target/{target}/achievement/{achievement}")
	public Map<String, String> getParameterValues(@PathVariable String parameterType, @PathVariable boolean target,
			@PathVariable boolean achievement, Authentication authentication) {

		if (isNull(parameterType) || isNull(target) || isNull(achievement))
			return Collections.emptyMap();

		return parameterService.parameterValues(parameterType, target, achievement);
	}

	@GetMapping("/paramter_value")
	public Map<String, String> getAllParameterValues(Authentication authentication) {
		return parameterService.parameterAllValues();
	}

	/**
	 * @param authentication logged-in user details
	 * @return all data of particular organization
	 */
	@GetMapping("/noQualifierParameter")
	private List<Parameter> getNoQualifierParameterList(Authentication authentication, @PathVariable int page,
			@PathVariable int size) {
		if (!aclService.readPermission(authentication, Constants.PARAMETER))
			throw new AccessDenied(successMessage.aclReadPermission() + authentication.getName());

		return parameterService.getNoQualifierParameterList(authentication);

	}

	private void setAchievementAndTargetFalseIfNull(Parameter parameter) {
		if (ValidatorUtil.isNull(parameter.isAchievement()))
			parameter.setAchievement(false);
		if (ValidatorUtil.isNull(parameter.isTarget()))
			parameter.setTarget(false);
	}

}