package pl.edu.wat.shoeshop.reflection;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;          //reprezentacja typu java
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import pl.edu.wat.shoeshop.reflection.FieldInformation;
import net.bytebuddy.pool.TypePool;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Reflection {
    private TypeDescription entityDefinition;
    private TypeDescription requestDefinition;
    private TypeDescription responseDefinition;
    private TypeDescription mapperDefinition;
    private TypePool typePool;
    private ByteBuddy byteBuddy;


    public Reflection() {
        this.typePool = TypePool.Default.ofSystemLoader();
        this.byteBuddy = new ByteBuddy();
        this.entityDefinition = typePool.describe("pl.edu.wat.shoeshop.entity.Owner").resolve();
        this.requestDefinition = typePool.describe("pl.edu.wat.shoeshop.dto.OwnerRequest").resolve();
        this.responseDefinition = typePool.describe("pl.edu.wat.shoeshop.dto.OwnerResponse").resolve();
        this.mapperDefinition = typePool.describe("pl.edu.wat.shoeshop.mapper.OwnerMapper").resolve();
    }
    /**
     * @param fieldName1- lokalizacja
     * @param fieldType1- typ lokalizacji- String
     * @param fieldName2- miasto
     * @param fieldType2- typ lokalizacji- String
     */
    public static void apply(String fieldName1, String fieldType1, String fieldName2, String fieldType2,
                             String fieldName3, String fieldType3) {
        List<String> fieldName = new ArrayList<String>();
        List<String> fieldType = new ArrayList<String>();
        fieldName.add(fieldName1);
        fieldName.add(fieldName2);
        fieldType.add(fieldType1);
        fieldType.add(fieldType2);
        fieldName.add(fieldName3);
        fieldType.add(fieldType3);


        var ref = new Reflection();
        ref.applyEntity(fieldName, fieldType);
        ref.applyRequest(fieldName, fieldType);
        ref.applyResponse(fieldName, fieldType);
        ref.applyOwnerMapper(fieldName);
    }
    /**
     * DynamicType.Builder - konstruktor do tworzenia typu dynamicznego
     */
    public void applyEntity(List<String> fieldName, List<String> fieldType) {
        DynamicType.Builder<Object> builder = byteBuddy
                .redefine(entityDefinition,
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                //.defineProperty(fieldName1, typePool.describe(fieldType1).resolve());
                .defineProperty(fieldName.get(0), typePool.describe(fieldType.get(0)).resolve());        //doddane
                //.defineProperty(fieldName2, typePool.describe(fieldType2).resolve());       //dodane

        for( int i = 1; i < fieldName.size() ; i++){
            builder = builder.defineProperty(fieldName.get(i), typePool.describe(fieldType.get(i)).resolve());
        }

        try (var unloadedOwner = builder.make()) {
            entityDefinition = unloadedOwner.load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getTypeDescription();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void applyRequest(List<String> fieldName, List<String> fieldType) {
        DynamicType.Builder<Object> builder = byteBuddy
                .redefine(requestDefinition,
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                //.defineProperty(fieldName, typePool.describe(fieldType).resolve());
                .defineProperty(fieldName.get(0), typePool.describe(fieldType.get(0)).resolve());        //for
                //.defineProperty(fieldName2, typePool.describe(fieldType2).resolve());

        for( int i = 1; i < fieldName.size() ; i++){
            builder = builder.defineProperty(fieldName.get(i), typePool.describe(fieldType.get(i)).resolve());
        }

//            for(...) {
 //               builder = builder.defineProperty(sdasd, typePool.describe(asd).resolve())
   //         }
        //request entity
        try (var unloadedOwner = builder.make()) {
            requestDefinition = unloadedOwner.load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getTypeDescription();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private void applyResponse(List<String> fieldName, List<String> fieldType) {
        DynamicType.Builder<Object> builder = byteBuddy
                .redefine(responseDefinition,
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                //.defineProperty(fieldName, typePool.describe(fieldType).resolve());
                .defineProperty(fieldName.get(0), typePool.describe(fieldType.get(0)).resolve());
                //.defineProperty(fieldName2, typePool.describe(fieldType2).resolve());

        for( int i = 1; i < fieldName.size() ; i++){
            builder = builder.defineProperty(fieldName.get(i), typePool.describe(fieldType.get(i)).resolve());
        }


        try (var unloadedOwner = builder.make()) {
            responseDefinition = unloadedOwner.load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getTypeDescription();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  ?????TypeDescription- reprezentuje typ danych znaleziony pod daną ścieżką????
     *
     *  .byteBuddy -> generowanie i modyfikacja klas w czasie rzeczywistym
     *  .typePool -> umozliwienie odwolania się do pola za pomocą nazwy
     *  .default.ofSystemLoader -> wyszukanie danych przez zapytanie do klas systemowych
     *  .describe -> wyszukuje i opisuje dany typ wg nazwy
     */


    private void applyOwnerMapper(List<String> fieldName) {
        ByteBuddy byteBuddy = new ByteBuddy();      // tworzenie nowego obiektu ByteBuddy
        Implementation methodCallfillOwnerRequest = null;
        Implementation methodCallfillOwner = null;


        for (int i = 0; i < fieldName.size(); i++) {
            MethodCall fillOwnerRequest = MethodCall.invoke(setterOwnerEntity(fieldName.get(i)))
                    .onArgument(0)
                    .withMethodCall(MethodCall
                            .invoke(getterRequest(fieldName.get(i)))
                            .onArgument(1));

            MethodCall fillOwner = MethodCall.invoke(setterOwnerResponse(fieldName.get(i)))
                    .onArgument(0)
                    .withMethodCall(MethodCall
                            .invoke(getterEntity(fieldName.get(i)))
                            .onArgument(1));

            if (methodCallfillOwnerRequest == null) {
                methodCallfillOwnerRequest = fillOwnerRequest;
            } else {
                methodCallfillOwnerRequest = fillOwnerRequest.andThen(methodCallfillOwnerRequest);
            }

            if (methodCallfillOwner == null) {
                methodCallfillOwner = fillOwner;
            } else {
                methodCallfillOwner = fillOwner.andThen(methodCallfillOwner);
            }
        }

        DynamicType.Builder<Object> builder = byteBuddy
                .redefine(mapperDefinition,
                        ClassFileLocator.ForClassLoader.ofSystemLoader())
                .method(named("fillOwnerRequest"))
                .intercept(methodCallfillOwnerRequest)
                .method(named("fillOwner"))
                .intercept(methodCallfillOwner);

        try (var unloadedOwner = builder.make()) {
            mapperDefinition =  unloadedOwner.load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                    .getTypeDescription();
        } catch (IOException e) {
            throw new RuntimeException(e);}
    }


    /**
     * @param fieldName
     * @return
     */

    private MethodDescription getterEntity(String fieldName) {
        return entityDefinition
                .getDeclaredMethods()
                .filter(ElementMatchers.isGetter(fieldName))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    private MethodDescription setterOwnerResponse(String fieldName) {
        return responseDefinition
                .getDeclaredMethods()
                .filter(ElementMatchers.isSetter(fieldName))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    private MethodDescription getterRequest(String fieldName) {
        return requestDefinition
                .getDeclaredMethods()
                .filter(ElementMatchers.isGetter(fieldName))
                .stream()
                .findFirst()
                .orElseThrow();
    }

    private MethodDescription setterOwnerEntity(String fieldName) {
        return entityDefinition
                .getDeclaredMethods()
                .filter(ElementMatchers.isSetter(fieldName))
                .stream()
                .findFirst()
                .orElseThrow();
    }

}