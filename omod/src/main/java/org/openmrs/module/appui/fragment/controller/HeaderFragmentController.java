/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.appui.fragment.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.appframework.domain.Extension;
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.openmrs.module.appui.AppUiExtensions;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.util.PrivilegeConstants;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class HeaderFragmentController {

    public void controller(@SpringBean AppFrameworkService appFrameworkService, FragmentModel fragmentModel) {
        try {
            Context.addProxyPrivilege(PrivilegeConstants.VIEW_LOCATIONS);
            fragmentModel.addAttribute("loginLocations", appFrameworkService.getLoginLocations());

            List<Extension> exts = appFrameworkService.getExtensionsForCurrentUser(AppUiExtensions.HEADER_CONFIG_EXTENSION);
            Map<String, Object> configSettings = exts.size() > 0 ? exts.get(0).getExtensionParams() : null;
            fragmentModel.addAttribute("configSettings", configSettings);
        } finally {
            Context.removeProxyPrivilege(PrivilegeConstants.VIEW_LOCATIONS);
        }
    }

}
