package io.mathan.raml.jaxrs.plugins.jsr250.sample.types;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import org.junit.Test;

public class NoneShallPassTest {

  @Test
  public void noneShallPassClass() {
    AnnotationTestUtils.assertAnnotationPresent(NoneShallPass.class, DenyAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(NoneShallPass.class, PermitAll.class, RolesAllowed.class);
  }

  @Test
  public void getNoneShallPass() throws NoSuchMethodException {
    AnnotationTestUtils.assertAnnotationNotPresent(NoneShallPass.class.getMethod("getNoneShallPass"), DenyAll.class, PermitAll.class, RolesAllowed.class);
  }

  @Test
  public void getNoneShallPassById() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(NoneShallPass.class.getMethod("getNoneShallPassById", String.class), RolesAllowed.class);
    AnnotationTestUtils.assertAnnotationNotPresent(NoneShallPass.class.getMethod("getNoneShallPassById", String.class), DenyAll.class, PermitAll.class);
  }

  @Test
  public void putNoneShallPassById() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(NoneShallPass.class.getMethod("putNoneShallPassById", String.class, String.class), PermitAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(NoneShallPass.class.getMethod("putNoneShallPassById", String.class, String.class), RolesAllowed.class, DenyAll.class);
  }

}
