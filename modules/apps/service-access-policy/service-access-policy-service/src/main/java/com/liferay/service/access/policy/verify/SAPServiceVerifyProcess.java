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

package com.liferay.service.access.policy.verify;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.verify.VerifyProcess;
import com.liferay.service.access.policy.service.SAPEntryLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true,
	property = {"verify.process.name=com.liferay.service.access.policy.service"},
	service = VerifyProcess.class
)
public class SAPServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyDefaultSAPEntry();
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setSAPEntryLocalService(
		SAPEntryLocalService sapEntryLocalService) {

		_sapEntryLocalService = sapEntryLocalService;
	}

	protected void verifyDefaultSAPEntry() {
		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			try {
				_sapEntryLocalService.checkSystemSAPEntries(
					company.getCompanyId());
			}
			catch (PortalException pe) {
				_log.error(
					"Unable to add default service access policy for company " +
						company.getCompanyId(),
					pe);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SAPServiceVerifyProcess.class);

	private CompanyLocalService _companyLocalService;
	private SAPEntryLocalService _sapEntryLocalService;

}