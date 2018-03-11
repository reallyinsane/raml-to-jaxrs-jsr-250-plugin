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

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import org.raml.jaxrs.generator.extension.resources.api.GlobalResourceExtension;
import org.raml.jaxrs.generator.extension.resources.api.ResourceContext;
import org.raml.jaxrs.generator.ramltypes.GMethod;
import org.raml.jaxrs.generator.ramltypes.GRequest;
import org.raml.jaxrs.generator.ramltypes.GResource;
import org.raml.jaxrs.generator.ramltypes.GResponse;
import org.raml.ramltopojo.EventType;
import org.raml.ramltopojo.extensions.ObjectPluginContext;
import org.raml.ramltopojo.extensions.ObjectTypeHandlerPlugin;
import org.raml.v2.api.model.v10.datamodel.ObjectTypeDeclaration;

/**
 * Support for {@link javax.annotation.security.DenyAll} annotation on generated resources or
 * resource methods.
 */
public class DenyAllPlugin implements GlobalResourceExtension {

  public Builder onResource(ResourceContext context, GResource resource, Builder typeSpec) {
    typeSpec.addAnnotation(javax.annotation.security.DenyAll.class);
    return typeSpec;
  }

  public MethodSpec.Builder onMethod(ResourceContext context, GMethod method, GRequest gRequest,
      MethodSpec.Builder methodSpec) {
    methodSpec.addAnnotation(javax.annotation.security.DenyAll.class);
    return methodSpec;
  }

  public Builder onResponseClass(ResourceContext context, GMethod method, Builder typeSpec) {
    return null;
  }

  public MethodSpec.Builder onMethod(ResourceContext context, GResponse responseMethod,
      MethodSpec.Builder methodSpec) {
    return null;
  }
}
