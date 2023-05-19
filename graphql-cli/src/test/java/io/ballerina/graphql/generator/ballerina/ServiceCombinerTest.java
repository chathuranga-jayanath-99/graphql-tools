package io.ballerina.graphql.generator.ballerina;

import io.ballerina.compiler.syntax.tree.ModulePartNode;
import io.ballerina.compiler.syntax.tree.NodeParser;
import io.ballerina.compiler.syntax.tree.SyntaxTree;
import io.ballerina.graphql.cmd.Constants;
import io.ballerina.graphql.cmd.Utils;
import io.ballerina.graphql.common.GraphqlTest;
import io.ballerina.graphql.generator.service.GraphqlServiceProject;
import io.ballerina.graphql.generator.service.ServiceCombiner;
import io.ballerina.graphql.generator.service.generator.ServiceTypesGenerator;
import org.ballerinalang.formatter.core.Formatter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.ballerina.graphql.generator.CodeGeneratorConstants.ROOT_PROJECT_NAME;

/**
 * Test class for ServiceCombiner.
 * Test the successful combination of available service file and generated service file from schema
 */
public class ServiceCombinerTest extends GraphqlTest {
    @Test(description = "Test combining updated schema with object type")
    public void testCombiningUpdatedSchemaWithObjectType() throws Exception {
        String updatedBalFileName = "typesWithSingleObjectDefault";
        String newSchemaFileName = "SchemaWithSingleObjectApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", updatedBalFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addType", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addType", updatedBalFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with enum")
    public void testCombiningUpdatedSchemaWithEnum() throws Exception {
        String balFileName = "typesWithEnumDefault";
        String newSchemaFileName = "SchemaWithEnumApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addType", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addType", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with interface")
    public void testCombiningUpdatedSchemaWithInterface() throws Exception {
        String balFileName = "typesWithInterfaceDefault";
        String newSchemaFileName = "SchemaWithInterfaceApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addType", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addType", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }


    @Test(description = "Test combining updated schema with input object")
    public void testCombiningUpdatedSchemaWithInputObject() throws Exception {
        String balFileName = "typesWithInputsDefault";
        String newSchemaFileName = "SchemaWithInputsApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addType", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addType", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with union")
    public void testCombiningUpdatedSchemaWithUnion() throws Exception {
        String balFileName = "typesWithUnionDefault";
        String newSchemaFileName = "SchemaWithUnionApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addType", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addType", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with object use records if possible")
    public void testCombiningUpdatedSchemaWithObjectUseRecordsIfPossible() throws Exception {
        String balFileName = "typesWithObjectTakingInputArgumentRecordsAllowed";
        String newSchemaFileName = "SchemaWithObjectTakingInputArgumentApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addType", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addType", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setUseRecordsForObjects(true);
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with new query fields")
    public void testCombiningUpdatedSchemaWithNewQueryFields() throws Exception {
        String balFileName = "typesWithQueryDefault";
        String newSchemaFileName = "SchemaWithQueryApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addField", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addField", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with new mutation fields")
    public void testCombiningUpdatedSchemaWithNewMutationFields() throws Exception {
        String balFileName = "typesWithMutationDefault";
        String newSchemaFileName = "SchemaWithMutationApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addField", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addField", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with new subscription fields")
    public void testCombiningUpdatedSchemaWithNewSubscriptionFields() throws Exception {
        String balFileName = "typesWithSubscriptionDefault";
        String newSchemaFileName = "SchemaWithSubscriptionApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addField", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addField", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with new enum fields")
    public void testCombiningUpdatedSchemaWithNewEnumFields() throws Exception {
        String balFileName = "typesWithEnumDefault";
        String newSchemaFileName = "SchemaWithEnumApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addField", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addField", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }

    @Test(description = "Test combining updated schema with new input type fields")
    public void testCombiningUpdatedSchemaWithNewInputTypeFields() throws Exception {
        String balFileName = "typesWithInputsDefault";
        String newSchemaFileName = "SchemaWithInputsApi";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", "onlyLogicImplementation", balFileName + ".bal"));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", "addField", newSchemaFileName + ".graphql"));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", "addField", balFileName + ".bal"));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceCombiner serviceCombiner = new ServiceCombiner(updateBalFileNode, nextSchemaNode);
        SyntaxTree mergedSyntaxTree = serviceCombiner.mergeRootNodes();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);
    }
}
