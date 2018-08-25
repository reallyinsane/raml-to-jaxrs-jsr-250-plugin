package io.mathan.raml.jaxrs.plugins.jsr250.sample.types;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import org.junit.Test;

public class WorthyPassTest {

  @Test
  public void worthyPassClass() {
    AnnotationTestUtils.assertAnnotationPresent(WorthyPass.class, RolesAllowed.class);
    AnnotationTestUtils.assertAnnotationNotPresent(WorthyPass.class, PermitAll.class, DenyAll.class);
  }

  @Test
  public void getWorthyPass() throws NoSuchMethodException {
    AnnotationTestUtils.assertAnnotationNotPresent(WorthyPass.class.getMethod("getWorthyPass"), DenyAll.class, PermitAll.class, RolesAllowed.class);
  }

  @Test
  public void getWorthyPassById() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(WorthyPass.class.getMethod("getWorthyPassById", String.class), PermitAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(WorthyPass.class.getMethod("getWorthyPassById", String.class), DenyAll.class, RolesAllowed.class);
  }

  @Test
  public void putWorthyPassById() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(WorthyPass.class.getMethod("putWorthyPassById", String.class, String.class), DenyAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(WorthyPass.class.getMethod("putWorthyPassById", String.class, String.class), RolesAllowed.class, PermitAll.class);
  }

}
