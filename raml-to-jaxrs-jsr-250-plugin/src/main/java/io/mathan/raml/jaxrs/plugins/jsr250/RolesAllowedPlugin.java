/*
 * Copyright 2018 Matthias Hanisch
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.mathan.raml.jaxrs.plugins.jsr250;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.raml.jaxrs.generator.extension.resources.api.GlobalResourceExtension;
import org.raml.jaxrs.generator.extension.resources.api.ResourceContext;
import org.raml.jaxrs.generator.ramltypes.GMethod;
import org.raml.jaxrs.generator.ramltypes.GRequest;
import org.raml.jaxrs.generator.ramltypes.GResource;
import org.raml.jaxrs.generator.ramltypes.GResponse;

/**
 * Support for {@link javax.annotation.security.RolesAllowed} annotation on generated resources or
 * resource methods.
 */
public class RolesAllowedPlugin extends DefaultGlobalResourceExtension {

  private final List<String> arguments;

  public RolesAllowedPlugin(List<String> arguments) {
    this.arguments = arguments;
  }

  public Builder onResource(ResourceContext context, GResource resource, Builder typeSpec) {
    typeSpec.addAnnotation(createAnnotation());
    return typeSpec;
  }

  public MethodSpec.Builder onMethod(ResourceContext context, GMethod method, GRequest gRequest,
      MethodSpec.Builder methodSpec) {
    methodSpec.addAnnotation(createAnnotation());
    return methodSpec;
  }

  private AnnotationSpec createAnnotation() {
        return AnnotationSpec.builder(RolesAllowed.class)
            .addMember("value","$L",  "{\"" + String.join("\",\"", arguments) + "\"}")
            .build();
  }
}
