package io.mathan.raml.jaxrs.plugins.jsr250.sample.types;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import org.junit.Test;

public class AllPassTest {

  @Test
  public void allPassClass() {
    AnnotationTestUtils.assertAnnotationPresent(AllPass.class, PermitAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(AllPass.class, DenyAll.class, RolesAllowed.class);
  }

  @Test
  public void getAllPass() throws NoSuchMethodException {
    AnnotationTestUtils.assertAnnotationNotPresent(AllPass.class.getMethod("getAllPass"), DenyAll.class, PermitAll.class, RolesAllowed.class);
  }

  @Test
  public void getAllPassById() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(AllPass.class.getMethod("getAllPassById", String.class), RolesAllowed.class);
    AnnotationTestUtils.assertAnnotationNotPresent(AllPass.class.getMethod("getAllPassById", String.class), DenyAll.class, PermitAll.class);
  }

  @Test
  public void putAllPassById() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(AllPass.class.getMethod("putAllPassById", String.class, String.class), DenyAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(AllPass.class.getMethod("putAllPassById", String.class, String.class), RolesAllowed.class, PermitAll.class);
  }

}
