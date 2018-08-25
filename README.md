JSR-250 annotations for [raml-for-jax-rs](https://github.com/mulesoft-labs/raml-for-jax-rs) 
=====

With [raml-for-jax-rs 3.0](https://github.com/mulesoft-labs/raml-for-jax-rs/tree/3.0.1) the plugin mechanism to enhance the generation of JAX-RS resources is redesigned. This allows to write custom plugins enabling generation of dynamic content for the created JAX-RS resources. This project is one of the first using this extension.

When generating JAX-RS resources these should be secured by using JSR-250 annotations like ```@DenyAll```, ```@PermitAll``` and ```@RolesAllowed```. This project provides the plugins for raml-for-jax-rs to enable this feature for RAML files.

Maven Configuration
------------------- 
To enable the plugin it needs to be declared as dependency to raml-to-jaxrs-maven-plugin like this:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.raml.jaxrs</groupId>
      <artifactId>raml-to-jaxrs-maven-plugin</artifactId>
      <version>3.0.2</version>
      <dependencies>
        <dependency>
          <groupId>io.mathan.raml</groupId>
          <artifactId>raml-to-jaxrs-jsr-250-plugin</artifactId>
          <version>1.0.0-SNAPSHOT</version>
        </dependency>
      </dependencies>
      ...
    </plugin>
  </plugins>
</build>
```

Available plugins
-----------------
The supported plugins use the prefix ```jsr250```. The names match the JSR-250 annotation names.

JSR-250 annotation | Plugin name
-------------------|------------
@DenyAll           | jsr250.denyAll
@PermitAll         | jsr250.permitAll
@RolesAllowed      | jsr250.rolesAllowed

Usage
-----

To use the plugins you have to declare them on resource level or resource method level. For
_jsr250.denyAll_ and _jsr250.permitAll_ the declaration of the plugin name is sufficient. When
using _jsr250.rolesAllowed_ the roles to allow have to specified as arguments to the plugin.

The following sample RAML permits the access on resource level to all roles. The access to the get
method is limited to the role _SpecialRole_ and the access to the put method is denied. Please not
that any of the plugins can be declared on resource or resource method level. 

```yaml
/sample:
  (ramltojaxrs.resources):
    plugins:
      - name: jsr250.permitAll
  get:
    (ramltojaxrs.methods):
      plugins:
        - name: jsr250.rolesAllowed
          arguments: [SpecialRole]
    responses:
      200:
        body:
          type: BodyType
  put:
    (ramltojaxrs.methods):
      plugins:
        - name: jsr250.denyAll
    body:
      type: BodyType
    responses:
      200:
        body:
          type: BodyType
```

The result will then look like this:

```java
@Path("/sample")
@PermitAll
public interface Sample {
  @GET
  @Produces("application/json")
  @RolesAllowed({"SpecialRole"})
  @Consumes
  GetSampleResponse getSample();

  @PUT
  @Consumes("application/json")
  @DenyAll
  PutSampleResponse putSample(String entity);
}
```
