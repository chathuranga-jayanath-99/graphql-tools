package io.ballerina.graphql.generator.service;

import io.ballerina.compiler.syntax.tree.MethodDeclarationNode;
import io.ballerina.compiler.syntax.tree.Token;
import io.ballerina.graphql.generator.CodeGeneratorConstants;

import static io.ballerina.graphql.generator.service.EqualityResultUtils.getMainQualifier;
import static io.ballerina.graphql.generator.service.EqualityResultUtils.getMergedMethodDeclarationQualifiers;
import static io.ballerina.graphql.generator.service.EqualityResultUtils.getMethodDeclarationName;
import static io.ballerina.graphql.generator.service.EqualityResultUtils.getMethodDeclarationResolverType;
import static io.ballerina.graphql.generator.service.EqualityResultUtils.isMetadataEqual;
import static io.ballerina.graphql.generator.service.EqualityResultUtils.isRelativeResourcePathEquals;

/**
 * Utility class used to store result comparing two method declarations.
 */
public class MethodDeclarationEqualityResult {
    private FunctionSignatureEqualityResult functionSignatureEqualityResult;
    private MethodDeclarationNode prevMethodDeclaration;
    private MethodDeclarationNode nextMethodDeclaration;

    public MethodDeclarationEqualityResult(MethodDeclarationNode prevMethodDeclaration,
                                           MethodDeclarationNode nextMethodDeclaration) {
        this.prevMethodDeclaration = prevMethodDeclaration;
        this.nextMethodDeclaration = nextMethodDeclaration;
    }

    public boolean isMatch() {
        return getMethodDeclarationName(prevMethodDeclaration).equals(getMethodDeclarationName(nextMethodDeclaration));
    }

    public boolean isEqual() {
        return isQualifiersEquals() && isMetadataEqual(prevMethodDeclaration.metadata().orElse(null),
                nextMethodDeclaration.metadata().orElse(null)) && isMethodNameEquals() &&
                isRelativeResourcePathEquals(prevMethodDeclaration.relativeResourcePath(),
                        nextMethodDeclaration.relativeResourcePath()) && functionSignatureEqualityResult.isEqual();
    }

    private boolean isQualifiersEquals() {
        if (prevMethodDeclaration.qualifierList().size() == nextMethodDeclaration.qualifierList().size()) {
            for (int i = 0; i < prevMethodDeclaration.qualifierList().size(); i++) {
                String prevQualifierName = prevMethodDeclaration.qualifierList().get(i).text();
                String nextQualifierName = nextMethodDeclaration.qualifierList().get(i).text();
                if (!prevQualifierName.equals(nextQualifierName)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean isMethodNameEquals() {
        return prevMethodDeclaration.methodName().text().equals(nextMethodDeclaration.methodName().text());
    }

    public FunctionSignatureEqualityResult getFunctionSignatureEqualityResult() {
        return functionSignatureEqualityResult;
    }

    public void setFunctionSignatureEqualityResult(FunctionSignatureEqualityResult functionSignatureEqualityResult) {
        this.functionSignatureEqualityResult = functionSignatureEqualityResult;
    }

    public String getPrevFunctionName() {
        return getMethodDeclarationName(prevMethodDeclaration);
    }

    public String getPrevMainQualifier() {
        return getMainQualifier(prevMethodDeclaration.qualifierList()).text();
    }

    public String getNextMainQualifier() {
        return getMainQualifier(nextMethodDeclaration.qualifierList()).text();
    }

    public String getPrevMethodType() {
        return getMethodDeclarationResolverType(prevMethodDeclaration);
    }

    public String getNextMethodType() {
        return getMethodDeclarationResolverType(nextMethodDeclaration);
    }

    public boolean isGetAndSubscribeInterchanged() {
        String prevMethodType = getMethodDeclarationResolverType(prevMethodDeclaration);
        String nextMethodType = getMethodDeclarationResolverType(nextMethodDeclaration);
        if (prevMethodType != null && nextMethodType != null) {
            return (prevMethodType.equals(CodeGeneratorConstants.GET) &&
                    nextMethodType.equals(CodeGeneratorConstants.SUBSCRIBE)) ||
                    (prevMethodType.equals(CodeGeneratorConstants.SUBSCRIBE) &&
                            nextMethodType.equals(CodeGeneratorConstants.GET));
        }
        return false;
    }

    public boolean isQualifierSimilar() {
        Token prevMainQualifier = getMainQualifier(prevMethodDeclaration.qualifierList());
        Token nextMainQualifier = getMainQualifier(nextMethodDeclaration.qualifierList());
        if (prevMainQualifier != null && nextMainQualifier != null) {
            return prevMainQualifier.text().equals(nextMainQualifier.text());
        }
        return prevMainQualifier == null && nextMainQualifier == null;
    }

    public MethodDeclarationNode generateCombinedMethodDeclaration() {
        return prevMethodDeclaration.modify(prevMethodDeclaration.kind(), nextMethodDeclaration.metadata().orElse(null),
                getMergedMethodDeclarationQualifiers(
                        prevMethodDeclaration.qualifierList(), nextMethodDeclaration.qualifierList()),
                nextMethodDeclaration.functionKeyword(),
                nextMethodDeclaration.methodName(), nextMethodDeclaration.relativeResourcePath(),
                nextMethodDeclaration.methodSignature(), nextMethodDeclaration.semicolon());
    }
}
