/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.coldFusion.UI.runner;

import com.intellij.coldFusion.model.files.CfmlFileType;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import icons.CFMLIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by Lera Nikolaenko
 */
public class CfmlRunConfigurationType implements ConfigurationType {
  private ConfigurationFactory myConfigurationFactory;

  public CfmlRunConfigurationType() {
    myConfigurationFactory = new ConfigurationFactory(this) {
      @Override
      public boolean isApplicable(@NotNull Project project) {
        return FileTypeIndex.containsFileOfType(CfmlFileType.INSTANCE, GlobalSearchScope.projectScope(project));
      }

      @NotNull
      public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new CfmlRunConfiguration(project, this, "Cold Fusion");
      }
    };
  }


  public String getDisplayName() {
    return "Cold Fusion";
  }

  public String getConfigurationTypeDescription() {
    return "Cold Fusion runner description";
  }

  public Icon getIcon() {
    return CFMLIcons.Cfml;
  }

  public static CfmlRunConfigurationType getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(CfmlRunConfigurationType.class);
  }

  @NotNull
  public String getId() {
    return getConfigurationTypeDescription();
  }

  public ConfigurationFactory[] getConfigurationFactories() {
    return new ConfigurationFactory[]{myConfigurationFactory};
  }

}
