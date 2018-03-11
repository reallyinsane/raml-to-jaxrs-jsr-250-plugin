 Plugin for the upcoming 3.0.0 release of [raml-for-jax-rs](https://github.com/mulesoft-labs/raml-for-jax-rs) providing @DenyAll, @PermitAll and @RolesAllowed
=====

When generating java classes of REST resources you might want to secure the REST services with JSR-250 annotations. To add custom code to the generated Java classes you have to use plugins. With version 3.0.0 of raml-for-jav-rs this mechanism has been enhanced. So this
plugin was created to make it even easier to apply JSR-250 annotations.

You have to use this plugin when generating Java sources with raml-to-jaxrs. A sample configuration looks like this:

```
	<build>
		<plugins>
			<plugin>
				<groupId>org.raml.jaxrs</groupId>
				<artifactId>raml-to-jaxrs-maven-plugin</artifactId>
				<version>3.0.0-SNAPSHOT</version>
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

For each JSR-250 annotation you want to use you have to declare a specific plugin in your RAML file.

JSR-250 annotation | Plugin name
-------------------|------------
@DenyAll           | jsr250.denyAll
@PermitAll         | jsr250.permitAll
@RolesAllowed      | jsr250.rolesAllowed

The declaration can be done on resource level or method level:

DenyAll
-------
To generate @DenyAll annotation just declare the plugin on the resource or resource method.

```
/noneShallPass:
  (ramltojaxrs.resources):
    plugins:
      - name: jsr250.denyAll
``` 

```
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

PermitAll
-------
To generate @PermitAll annotation just declare the plugin on the resource or resource method.

```
/noneShallPass:
  (ramltojaxrs.resources):
    plugins:
      - name: jsr250.permitAll
``` 

```
put:
  (ramltojaxrs.methods):
    plugins:
      - name: jsr250.permitAll
  body:
    type: BodyType
  responses:
    200:
      body:
        type: BodyType          
```

RolesAllowed
-------
To generate @RolesAllowed annotation just declare the plugin on the resource or resource method and specify the roles to allow in the arguments array.

```
/noneShallPass:
  (ramltojaxrs.resources):
    plugins:
      - name: jsr250.rolesAllowed
        arguments: [SpecialRole]
``` 

```
put:
  (ramltojaxrs.methods):
    plugins:
      - name: jsr250.rolesAllowed
        arguments: [SpecialRole, SpecialRole2]
  body:
    type: BodyType
  responses:
    200:
      body:
        type: BodyType          
```
