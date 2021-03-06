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

package com.liferay.site.item.selector.web.display.context;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portlet.usersadmin.search.GroupSearch;
import com.liferay.site.item.selector.criterion.SiteItemSelectorCriterion;
import com.liferay.site.util.RecentGroupManager;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class RecentSitesItemSelectorViewDisplayContext
	extends BaseSitesItemSelectorViewDisplayContext
	implements SitesItemSelectorViewDisplayContext {

	public RecentSitesItemSelectorViewDisplayContext(
		HttpServletRequest request,
		SiteItemSelectorCriterion siteItemSelectorCriterion,
		String itemSelectedEventName, PortletURL portletURL,
		RecentGroupManager recentGroupManager) {

		super(
			request, siteItemSelectorCriterion, itemSelectedEventName,
			portletURL);

		_recentGroupManager = recentGroupManager;
	}

	@Override
	public GroupSearch getGroupSearch() throws Exception {
		GroupSearch groupSearch = new GroupSearch(
			getPortletRequest(), getPortletURL());

		groupSearch.setEmptyResultsMessage(
			"you-have-not-visited-any-sites-recently");

		List<Group> results = _recentGroupManager.getRecentGroups(request);

		groupSearch.setTotal(results.size());
		groupSearch.setResults(results);

		return groupSearch;
	}

	private final RecentGroupManager _recentGroupManager;

}