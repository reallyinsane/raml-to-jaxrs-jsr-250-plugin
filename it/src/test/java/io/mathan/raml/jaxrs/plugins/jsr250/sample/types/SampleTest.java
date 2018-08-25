package io.mathan.raml.jaxrs.plugins.jsr250.sample.types;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import org.junit.Test;

public class SampleTest {
  @Test
  public void sampleClass() {
    AnnotationTestUtils.assertAnnotationPresent(Sample.class, PermitAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(Sample.class, DenyAll.class, RolesAllowed.class);
  }


  @Test
  public void getSample() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(Sample.class.getMethod("getSample"), RolesAllowed.class);
    AnnotationTestUtils.assertAnnotationNotPresent(Sample.class.getMethod("getSample"), DenyAll.class, PermitAll.class);
  }

  @Test
  public void putAllPassById() throws Exception {
    AnnotationTestUtils.assertAnnotationPresent(Sample.class.getMethod("putSample", String.class), DenyAll.class);
    AnnotationTestUtils.assertAnnotationNotPresent(Sample.class.getMethod("putSample", String.class), RolesAllowed.class, PermitAll.class);
  }
}
