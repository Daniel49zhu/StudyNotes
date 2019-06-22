import java.util.EnumSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner7;
import javax.tools.Diagnostic.Kind;

/**
 * javac NameCheckProcessor.java
 * javac -processor NameCheckProcessor FT.java
 *
 * @author kevin
 */


@SupportedAnnotationTypes(value = "*")
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.nameCheck = new NameCheck(processingEnv);
    }

    private NameCheck nameCheck;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameCheck.check(element);
            }
        }
        return false;
    }

    /**
     *
     * @author kevin
     */
    public static class NameCheck {
        Messager messager = null;
        public NameCheckScanner nameCheckScanner;

        private NameCheck(ProcessingEnvironment processingEnv) {
            messager = processingEnv.getMessager();
            nameCheckScanner = new NameCheckScanner(processingEnv);
        }

        /**
         *
         * @param element
         */
        public void check(Element element) {
            nameCheckScanner.scan(element);
        }

        /**
         *
         * @author kevin
         */
        public static class NameCheckScanner extends ElementScanner7<Void, Void> {
            Messager messager = null;

            public NameCheckScanner(ProcessingEnvironment processingEnv) {
                this.messager = processingEnv.getMessager();
            }

            /**
             */
            @Override
            public Void visitType(TypeElement e, Void p) {
                scan(e.getTypeParameters(), p);
                checkCamelCase(e, true);
                super.visitType(e, p);
                return null;
            }

            /**
             *
             * @param e
             */
            private void checkCamelCase(Element e, boolean initialCaps) {
                String name = e.getSimpleName().toString();
                boolean previousUpper = false;
                boolean conventional = true;
                int firstCodePoint = name.codePointAt(0);
                if (Character.isUpperCase(firstCodePoint)) {
                    previousUpper = true;
                    if (!initialCaps) {
                        messager.printMessage(Kind.WARNING, "xxxxx" + name + " xxxxxxxxx", e);
                        return;
                    }
                } else if (Character.isLowerCase(firstCodePoint)) {
                    if (initialCaps) {
                        messager.printMessage(Kind.WARNING, "xxxx" + name + "xxxxxxxxxx", e);
                        return;
                    }
                } else {
                    conventional = false;
                }
                if (conventional) {
                    int cp = firstCodePoint;
                    for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                        cp = name.codePointAt(i);
                        if (Character.isUpperCase(cp)) {
                            if (previousUpper) {
                                conventional = false;
                                break;
                            }
                            previousUpper = true;
                        } else {
                            previousUpper = false;
                        }
                    }
                }
                if (!conventional) {
                    messager.printMessage(Kind.WARNING, "xx" + name + "xxxxCamel Case Namexxxxx", e);
                }
            }

            /**
             */
            @Override
            public Void visitExecutable(ExecutableElement e, Void p) {
                if (e.getKind() == ElementKind.METHOD) {
                    Name name = e.getSimpleName();
                    if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                        messager.printMessage(Kind.WARNING, "xxxxx" + name + " x", e);
                        checkCamelCase(e, false);
                    }
                }
                super.visitExecutable(e, p);
                return null;
            }

            /**
             */
            @Override
            public Void visitVariable(VariableElement e, Void p) {
                if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                    checkAllCaps(e);
                } else {
                    checkCamelCase(e, false);
                }
                super.visitVariable(e, p);
                return null;
            }

            /**
             *
             * @param e
             */
            private void checkAllCaps(VariableElement e) {
                String name = e.getSimpleName().toString();
                boolean conventional = true;
                int firstCodePoint = name.codePointAt(0);
                if (!Character.isUpperCase(firstCodePoint)) {
                    conventional = false;
                } else {
                    boolean previousUnderscore = false;
                    int cp = firstCodePoint;
                    for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                        cp = name.codePointAt(i);
                        if (cp == (int) '_') {
                            if (previousUnderscore) {
                                conventional = false;
                                break;
                            }
                            previousUnderscore = true;
                        } else {
                            previousUnderscore = false;
                            if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                                conventional = false;
                                break;
                            }
                        }

                    }
                }
                if (!conventional) {
                    messager.printMessage(Kind.WARNING, "xx" + name + " xxxx" + "xx，xx", e);
                }
            }

            /**
             *
             * @param e
             * @return
             */
            private boolean heuristicallyConstant(VariableElement e) {
                if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                    return true;
                } else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(javax.lang.model.element.Modifier.FINAL, javax.lang.model.element.Modifier.STATIC, javax.lang.model.element.Modifier.PUBLIC))) {
                    return true;
                }
                return false;
            }
        }
    }

}

