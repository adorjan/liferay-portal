/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.dynamic.data.lists.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service interface for DDLRecordSet. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecordSetServiceUtil
 * @see com.liferay.dynamic.data.lists.service.base.DDLRecordSetServiceBaseImpl
 * @see com.liferay.dynamic.data.lists.service.impl.DDLRecordSetServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=ddl", "json.web.service.context.path=DDLRecordSet"}, service = DDLRecordSetService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DDLRecordSetService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDLRecordSetServiceUtil} to access the d d l record set remote service. Add custom service methods to {@link com.liferay.dynamic.data.lists.service.impl.DDLRecordSetServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public DDLRecordSet addRecordSet(long groupId, long ddmStructureId,
		java.lang.String recordSetKey, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, int minDisplayRows,
		int scope, ServiceContext serviceContext) throws PortalException;

	public void deleteRecordSet(long recordSetId) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDLRecordSet getRecordSet(long recordSetId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> getRecordSets(long[] groupIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> search(long companyId, long groupId,
		java.lang.String keywords, int scope, int start, int end,
		OrderByComparator<DDLRecordSet> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDLRecordSet> search(long companyId, long groupId,
		java.lang.String name, java.lang.String description, int scope,
		boolean andOperator, int start, int end,
		OrderByComparator<DDLRecordSet> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId,
		java.lang.String keywords, int scope);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(long companyId, long groupId, java.lang.String name,
		java.lang.String description, int scope, boolean andOperator);

	public DDLRecordSet updateMinDisplayRows(long recordSetId,
		int minDisplayRows, ServiceContext serviceContext)
		throws PortalException;

	public DDLRecordSet updateRecordSet(long groupId, long ddmStructureId,
		java.lang.String recordSetKey, Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, int minDisplayRows,
		ServiceContext serviceContext) throws PortalException;

	public DDLRecordSet updateRecordSet(long recordSetId, long ddmStructureId,
		Map<Locale, java.lang.String> nameMap,
		Map<Locale, java.lang.String> descriptionMap, int minDisplayRows,
		ServiceContext serviceContext) throws PortalException;

	public DDLRecordSet updateRecordSet(long recordSetId,
		DDMFormValues settingsDDMFormValues) throws PortalException;
}