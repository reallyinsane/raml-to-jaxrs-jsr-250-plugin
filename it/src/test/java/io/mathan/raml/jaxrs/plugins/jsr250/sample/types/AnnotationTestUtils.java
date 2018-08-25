package io.mathan.raml.jaxrs.plugins.jsr250.sample.types;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import org.junit.Assert;

public class AnnotationTestUtils {

  public static void assertAnnotationPresent(AnnotatedElement element, Class annotation) {
    assertAnnotationPresent(element, annotation, null);
  }

  public static void assertAnnotationPresent(AnnotatedElement element, Class annotationClass, String[] value) {
    Annotation annotation = element.getAnnotation(annotationClass);
    Assert.assertNotNull(String.format("Annotation @%s should be present", annotationClass.getName()), annotation);
  }

  public static void assertAnnotationNotPresent(AnnotatedElement element, Class... annotationClasses) {
    for(Class annotationClass:annotationClasses) {
      Annotation annotation = element.getAnnotation(annotationClass);
      Assert.assertNull(String.format("Annotation @%s should not be present", annotationClass.getName()), annotation);
    }
  }

}
