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

package com.liferay.wiki.web.portlet.configuration.icon;

import com.liferay.portal.kernel.portlet.configuration.icon.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.wiki.constants.WikiPortletKeys;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.service.permission.WikiNodePermissionChecker;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Roberto Díaz
 */
public class ViewNodeDeletedAttachmentsPortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public ViewNodeDeletedAttachmentsPortletConfigurationIcon(
		PortletRequest portletRequest, WikiNode node) {

		super(portletRequest);

		_node = node;
	}

	@Override
	public String getMessage() {
		return "view-removed-attachments";
	}

	@Override
	public String getURL() {
		PortletURL portletURL = PortalUtil.getControlPanelPortletURL(
			portletRequest, WikiPortletKeys.WIKI_ADMIN,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/wiki/view_node_deleted_attachments");
		portletURL.setParameter("redirect", themeDisplay.getURLCurrent());
		portletURL.setParameter("nodeId", String.valueOf(_node.getNodeId()));
		portletURL.setParameter(
			"viewTrashAttachments", Boolean.TRUE.toString());

		return portletURL.toString();
	}

	@Override
	public boolean isShow() {
		return WikiNodePermissionChecker.contains(
			themeDisplay.getPermissionChecker(), _node, ActionKeys.UPDATE);
	}

	private final WikiNode _node;

}