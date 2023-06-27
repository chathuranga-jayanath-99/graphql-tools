package io.ballerina.graphql.generator.ballerina;

import io.ballerina.compiler.syntax.tree.ModulePartNode;
import io.ballerina.compiler.syntax.tree.NodeParser;
import io.ballerina.compiler.syntax.tree.SyntaxTree;
import io.ballerina.graphql.cmd.Constants;
import io.ballerina.graphql.cmd.Utils;
import io.ballerina.graphql.common.GraphqlTest;
import io.ballerina.graphql.generator.service.GraphqlServiceProject;
import io.ballerina.graphql.generator.service.combiner.ServiceTypesFileCombiner;
import io.ballerina.graphql.generator.service.generator.ServiceTypesGenerator;
import org.ballerinalang.formatter.core.Formatter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.ballerina.graphql.generator.CodeGeneratorConstants.ROOT_PROJECT_NAME;

/**
 * Test class for ServiceCombiner.
 * Test the successful combination of available service file and generated service file from schema
 */
public class ServiceTypesFileCombinerTest extends GraphqlTest {
    @Test
    public void testCombiningUpdatedSchemaWithAddedNewObjectType() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewObjectTypeApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewObjectType.bal";
        String expectedBalFileName = "typesAfterAddingNewObjectType.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewEnum() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewEnumApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewEnum.bal";
        String expectedBalFileName = "typesAfterAddingNewEnum.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewInterface() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewInterfaceApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewInterface.bal";
        String expectedBalFileName = "typesAfterAddingNewInterface.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewInputType() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewInputTypeApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewInputType.bal";
        String expectedBalFileName = "typesAfterAddingNewInputType.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewUnion() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewUnionApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewUnion.bal";
        String expectedBalFileName = "typesAfterAddingNewUnion.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewObjectTypeUsingRecordsIfPossible() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewObjectTypeUsingRecordsIfPossibleApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewObjectTypeUsingRecordsIfPossible.bal";
        String expectedBalFileName = "typesAfterAddingNewObjectTypeUsingRecordsIfPossible.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setUseRecordsForObjects(true);
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewQueryFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewQueryFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewQueryFields.bal";
        String expectedBalFileName = "typesAfterAddingNewQueryFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewMutationFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewMutationFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewMutationFields.bal";
        String expectedBalFileName = "typesAfterAddingNewMutationFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewSubscriptionFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewSubscriptionFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewSubscriptionFields.bal";
        String expectedBalFileName = "typesAfterAddingNewSubscriptionFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewEnumFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewEnumFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewEnumFields.bal";
        String expectedBalFileName = "typesAfterAddingNewEnumFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewInputTypeFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewInputTypeFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewInputTypeFields.bal";
        String expectedBalFileName = "typesAfterAddingNewInputTypeFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(mergedSyntaxTree).toString().trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(result, expectedServiceTypesContent);

        List<String> warnings = new ArrayList<>();
        warnings.add("warning: In 'CreateAuthorInput' input type 'address' field is introduced without " +
                "a default value. This can brake available clients");
        warnings.add("warning: In 'CreateBookInput' input type 'version' field is introduced without a " +
                "default value. This can brake available clients");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warnings.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(warnings.get(i), breakingChangeWarnings.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewUnionMembers() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewUnionMembersApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewUnionMembers.bal";
        String expectedBalFileName = "typesAfterAddingNewUnionMembers.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewObjectTypeFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewObjectTypeFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewObjectTypeFields.bal";
        String expectedBalFileName = "typesAfterAddingNewObjectTypeFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewObjectTypeFieldsWhenRepresentedInRecords() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewObjectTypeFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewObjectTypeFieldsRecordsAllowed.bal";
        String expectedBalFileName = "typesAfterAddingNewObjectTypeFieldsRecordsAllowed.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        serviceTypesGenerator.setUseRecordsForObjects(true);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedObjectTypeFieldsWhenRepresentedInRecords() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedObjectTypeFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingObjectTypeFieldsRecordsAllowed.bal";
        String expectedBalFileName = "typesAfterRemovingObjectTypeFieldsRecordsAllowed.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        serviceTypesGenerator.setUseRecordsForObjects(true);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add(
                "warning: In 'Book' record type 'price' field has removed. This can brake clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithChangedObjectFieldTypeWhenRepresentedInRecords() throws Exception {
        String newSchemaFileName = "SchemaWithChangedObjectFieldTypeApi.graphql";
        String beforeBalFileName = "typesBeforeChangingObjectFieldTypeRecordsAllowed.bal";
        String expectedBalFileName = "typesAfterChangingObjectFieldTypeRecordsAllowed.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        serviceTypesGenerator.setUseRecordsForObjects(true);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add(
                "warning: In 'Book' record type 'id' field type has changed from 'int' to 'string'. This can break " +
                        "existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewObjectTypeFieldsInMultipleObjects() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewObjectTypeFieldsInMultipleObjectsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewObjectTypeFieldsInMultipleObjects.bal";
        String expectedBalFileName = "typesAfterAddingNewObjectTypeFieldsInMultipleObjects.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedNewInterfaceTypeFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewInterfaceTypeFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingNewInterfaceTypeFields.bal";
        String expectedBalFileName = "typesAfterAddingNewInterfaceTypeFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedInputTypeFields() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedInputTypeFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingInputTypeFields.bal";
        String expectedBalFileName = "typesAfterRemovingInputTypeFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add(
                "warning: In 'CreateAuthorInput' input type 'email' field has removed. This can brake clients.");
        warningMessages.add(
                "warning: In 'CreateBookInput' input type 'authorId' field has removed. This can brake clients.");
        warningMessages.add(
                "warning: In 'CreateBookInput' input type 'price' field has removed. This can brake clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 3);
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedEnumFields() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedEnumFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingEnumFields.bal";
        String expectedBalFileName = "typesAfterRemovingEnumFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Gender' enum 'FEMALE' member has removed. This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < 1; i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedObjectFieldsRepresentedInServiceClass() throws Exception {
        String newSchemaFileName = "SchemaWithSingleObjectRemoveFieldApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingFieldObject.bal";
        String expectedBalFileName = "typesAfterRemovingObjectField.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Book' service class 'title' function definition has removed. " +
                "This can break available clients");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        Assert.assertEquals(breakingChangeWarnings.get(0), warningMessages.get(0));
    }

    @Test
    public void testCombiningUpdatedSchemaWithChangedTypeInObjectFieldRepresentedInServiceClass() throws Exception {
        String newSchemaFileName = "SchemaWithSingleObjectChangedTypeInObjectFieldApi.graphql";
        String beforeBalFileName = "typesBeforeChangingTypeInSingleObjectField.bal";
        String expectedBalFileName = "typesAfterChangingTypeInSingleObjectField.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add(
                "warning: In 'Book' class 'price' function definition return type has changed from 'int' to 'float'. " +
                        "This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < warningMessages.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(0), warningMessages.get(0));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithChangedTypeInMultipleObjectFieldsRepresentedInServiceClass()
            throws Exception {
        String newSchemaFileName = "SchemaWithMultipleChangedTypesInObjectFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeChangingTypesInMultipleObjectFields.bal";
        String expectedBalFileName = "typesAfterChangingTypesInMultipleObjectFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Author' class 'id' function definition return type has changed from 'int' " +
                "to 'string'. This can break existing clients.");
        warningMessages.add(
                "warning: In 'Book' class 'id' function definition return type has changed from 'int' to 'string'. " +
                        "This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 2);
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedParametersInObjectFieldsRepresentedInServiceClass()
            throws Exception {
        String newSchemaFileName = "SchemaWithRemovedParametersInObjectFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingParametersInObjectFields.bal";
        String expectedBalFileName = "typesAfterRemovingParametersInObjectFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Adult' class 'age' function definition 'nic' parameter removed. " +
                "This can break existing clients.");
        warningMessages.add("warning: In 'Child' class 'pass' function definition 'score3' parameter removed. " +
                "This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedParametersInObjectFieldsRepresentedInServiceClass()
            throws Exception {
        String newSchemaFileName = "SchemaWithAddedParametersInObjectFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingParametersInObjectFields.bal";
        String expectedBalFileName = "typesAfterAddingParametersInObjectFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Child' class 'knowsWords' function definition 'word1' parameter added " +
                "without default value. This can break existing clients.");
        warningMessages.add(
                "warning: In 'Child' class 'knowsWords' function definition 'word2' parameter added without default " +
                        "value. This can break existing clients.");
        warningMessages.add(
                "warning: In 'Child' class 'knowsWords' function definition 'word' parameter removed. This can break " +
                        "existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedFieldsInQueryMutationAndSubscription() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedFieldsInQueryMutationAndSubscriptionApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingFieldsInQueryMutationAndSubscription.bal";
        String expectedBalFileName = "typesAfterRemovingFieldsInQueryMutationAndSubscription.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'SchemaWithRemovedFieldsInQueryMutationAndSubscriptionApi' service object " +
                "'books' method declaration has removed. This can break existing clients.");
        warningMessages.add(
                "warning: In 'SchemaWithRemovedFieldsInQueryMutationAndSubscriptionApi' service object 'updateBook' " +
                        "method declaration has removed. This can break existing clients.");
        warningMessages.add(
                "warning: In 'SchemaWithRemovedFieldsInQueryMutationAndSubscriptionApi' service object 'bookIds' " +
                        "method declaration has removed. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithRemovedFieldsInQueryMutationAndSubscriptionApi' service object " +
                "'authorIds' method declaration has removed. This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedParametersInQueryMutationAndSubscriptionFields() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedParametersInQueryMutationAndSubscriptionFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingParametersInQueryMutationAndSubscriptionFields.bal";
        String expectedBalFileName = "typesAfterRemovingParametersInQueryMutationAndSubscriptionFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'SchemaWithRemovedParametersInQueryMutationAndSubscriptionFieldsApi' service" +
                " object 'book' method declaration 'title' parameter has removed. This can break existing clients.");
        warningMessages.add(
                "warning: In 'SchemaWithRemovedParametersInQueryMutationAndSubscriptionFieldsApi' service object " +
                        "'addBook' method declaration 'authorId' parameter has removed. This can break existing " +
                        "clients.");
        warningMessages.add(
                "warning: In 'SchemaWithRemovedParametersInQueryMutationAndSubscriptionFieldsApi' service object " +
                        "'bookTitles' method declaration 'ids' parameter has removed. " +
                        "This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithTypesChangedInQueryMutationAndSubscriptionFields() throws Exception {
        String newSchemaFileName = "SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeChangingTypesInQueryMutationAndSubscriptionFields.bal";
        String expectedBalFileName = "typesAfterChangingTypesInQueryMutationAndSubscriptionFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'book' method declaration 'id' parameter type change from 'int' to 'string'. This can break " +
                "existing clients.");
        warningMessages.add(
                "warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service object 'book' " +
                        "method declaration return type has changed from 'Book?' to 'Book'. This can break existing " +
                        "clients.");
        warningMessages.add(
                "warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service object 'author' " +
                        "method declaration 'id' parameter type change from 'int' to 'string?'. This can break " +
                        "existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'author' method declaration return type has changed from 'Author?' to 'Author'. This can " +
                "break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'authors' method declaration return type has changed from 'Author[]' to 'Author?[]?'. This " +
                "can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'addBook' method declaration 'title' parameter type change from 'string' to 'string?'. This " +
                "can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'addBook' method declaration return type has changed from 'Book?' to 'Book'. This can break " +
                "existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'addAuthor' method declaration 'name' parameter type change from 'string' to 'string?'. This " +
                "can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'addAuthor' method declaration return type has changed from 'Author?' to 'Author'. This can " +
                "break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'bookTitles' method declaration 'ids' parameter type change from 'int?[]' to 'int?[]?'. This " +
                "can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'bookTitles' method declaration return type has changed from 'stream<string>' to " +
                "'stream<string?>'. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'authorNames' method declaration 'ids' parameter type change from 'int?[]' to 'int?[]?'. This" +
                " can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithTypesChangedInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'authorNames' method declaration return type has changed from 'stream<string>' to " +
                "'stream<string?>'. This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithAddedParametersInQueryMutationAndSubscriptionFields() throws Exception {
        String newSchemaFileName = "SchemaWithAddedParametersInQueryMutationAndSubscriptionFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeAddingParametersInQueryMutationAndSubscriptionFields.bal";
        String expectedBalFileName = "typesAfterAddingParametersInQueryMutationAndSubscriptionFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'SchemaWithAddedParametersInQueryMutationAndSubscriptionFieldsApi' service " +
                "object 'book' method declaration 'title' parameter added without default value. This can break " +
                "existing clients.");
        warningMessages.add(
                "warning: In 'SchemaWithAddedParametersInQueryMutationAndSubscriptionFieldsApi' service object " +
                        "'addBook' method declaration 'authorId' parameter added without default value. This can " +
                        "break existing clients.");
        warningMessages.add(
                "warning: In 'SchemaWithAddedParametersInQueryMutationAndSubscriptionFieldsApi' service object " +
                        "'authorNames' method declaration 'ids' parameter added without default value. This can break" +
                        " existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedParameterDefaultValues() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedParameterDefaultValuesApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingParameterDefaultValues.bal";
        String expectedBalFileName = "typesAfterRemovingParameterDefaultValues.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'SchemaWithRemovedParameterDefaultValuesApi' service object 'book' method " +
                "declaration 'id' parameter assigned '1' default value has removed. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithRemovedParameterDefaultValuesApi' service object 'book' method " +
                "declaration 'title' parameter assigned '\"No title\"' default value has removed. This can break " +
                "existing clients.");
        warningMessages.add("warning: In 'SchemaWithRemovedParameterDefaultValuesApi' service object 'authors' method" +
                " declaration 'ids' parameter assigned '[1]' default value has removed. This can break existing " +
                "clients.");
        warningMessages.add("warning: In 'SchemaWithRemovedParameterDefaultValuesApi' service object 'addBook' method" +
                " declaration 'authorId' parameter assigned '1' default value has removed. This can break existing " +
                "clients.");
        warningMessages.add("warning: In 'SchemaWithRemovedParameterDefaultValuesApi' service object 'addAuthor' " +
                "method declaration 'name' parameter assigned '\"No name\"' default value has removed. This can break" +
                " existing clients.");
        warningMessages.add("warning: In 'SchemaWithRemovedParameterDefaultValuesApi' service object 'bookTitles' " +
                "method declaration 'ids' parameter assigned '[]' default value has removed. This can break existing " +
                "clients.");
        warningMessages.add("warning: In 'CreateBookInput' record type 'price' field assigned '150.0' default value " +
                "has removed. This can break existing clients.");
        warningMessages.add("warning: In 'CreateBookInput' record type 'version' field assigned '\"v1.0\"' default " +
                "value has removed. This can break existing clients.");
        warningMessages.add("warning: In 'Author' service class 'name' function 'designation' parameter assigned " +
                "'\"\"' default value has removed. This can break existing clients.");
        warningMessages.add("warning: In 'Book' service class 'price' function 'copiesSold' parameter assigned '0' " +
                "default value has removed. This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedUnionMembers() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedUnionMembersApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingUnionMembers.bal";
        String expectedBalFileName = "typesAfterRemovingUnionMembers.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Profile' union type 'Parent' member has removed. This can break existing " +
                "clients.");
        warningMessages.add("warning: In 'Profile' union type 'Clerk' member has removed. This can break existing " +
                "clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedInterfaceFields() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedInterfaceFieldsApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingInterfaceFields.bal";
        String expectedBalFileName = "typesAfterRemovingInterfaceFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Info' interface service object 'phone' method declaration has removed. This" +
                " can break existing clients.");
        warningMessages.add("warning: In 'Info' interface service object 'email' method declaration has removed. This" +
                " can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRecordTypeFieldsTypeChanged() throws Exception {
        String newSchemaFileName = "SchemaWithChangedRecordTypeFieldsTypeApi.graphql";
        String beforeBalFileName = "typesBeforeChangingRecordTypeFieldsType.bal";
        String expectedBalFileName = "typesAfterChangingRecordTypeFieldsType.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setUseRecordsForObjects(true);
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'Author' record type 'id' field type has changed from 'string' to 'int'. " +
                "This can break existing clients.");
        warningMessages.add("warning: In 'Author' record type 'age' field type has changed from 'string' to 'int'. " +
                "This can break existing clients.");
        warningMessages.add("warning: In 'Book' record type 'id' field type has changed from 'string' to 'int'. This " +
                "can break existing clients.");
        warningMessages.add("warning: In 'Book' record type 'price' field type has changed from 'int' to 'float'. " +
                "This can break existing clients.");
        warningMessages.add("warning: In 'Book' record type 'soldAmount' field type has changed from 'float' to 'int'" +
                ". This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test(description = "Test combining updated schema with changed main qualifier in service object and service " +
            "class functions")
    public void testCombiningUpdatedSchemaWithChangedQualifiers() throws Exception {
        String newSchemaFileName = "SchemaWithChangedQualifiersApi.graphql";
        String beforeBalFileName = "typesBeforeChangingQualifiers.bal";
        String expectedBalFileName = "typesAfterChangingQualifiers.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'SchemaWithChangedQualifiersApi' service object 'book' " +
                "function qualifier list changed from 'remote' to 'resource'. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithChangedQualifiersApi' service object 'author' " +
                "function qualifier list changed from 'remote' to 'resource'. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithChangedQualifiersApi' service object 'addBook' " +
                "function qualifier list changed from 'resource' to 'remote'. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithChangedQualifiersApi' service object 'addAuthor' " +
                "function qualifier list changed from 'resource' to 'remote'. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithChangedQualifiersApi' service object 'authorNames'" +
                " function qualifier list changed from 'remote' to 'resource'. This can break existing clients.");
        warningMessages.add("warning: In 'Info' interface 'name' function qualifier list changed from 'remote' to " +
                "'resource'. This can break existing clients.");
        warningMessages.add("warning: In 'Author' service class 'name' function qualifier list changed from 'remote' " +
                "to 'resource'. This can break existing clients.");
        warningMessages.add("warning: In 'Book' service class 'price' function qualifier list changed from 'remote' " +
                "to 'resource'. This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithInterchangedQueryAndSubscriptionMethods() throws Exception {
        String newSchemaFileName = "SchemaWithInterchangedQueryAndSubscriptionMethodsApi.graphql";
        String beforeBalFileName = "typesBeforeInterchangingQueryAndSubscriptionMethods.bal";
        String expectedBalFileName = "typesAfterInterchangingQueryAndSubscriptionMethods.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'SchemaWithInterchangedQueryAndSubscriptionMethodsApi' service object " +
                "'authorNames' method changed from 'subscribe' to 'get'. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithInterchangedQueryAndSubscriptionMethodsApi' service object " +
                "'authorNames' method declaration return type has changed from 'stream<string>' to 'string'. This " +
                "can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithInterchangedQueryAndSubscriptionMethodsApi' service object 'book'" +
                " method changed from 'get' to 'subscribe'. This can break existing clients.");
        warningMessages.add("warning: In 'SchemaWithInterchangedQueryAndSubscriptionMethodsApi' service object 'book'" +
                " method declaration return type has changed from 'Book' to 'stream<Book>'. This can break existing " +
                "clients.");
        warningMessages.add("warning: In 'Info' service object 'bookNames' method changed from 'subscribe' to 'get'. " +
                "This can break existing clients.");
        warningMessages.add("warning: In 'Info' service object 'bookNames' method declaration return type has changed" +
                " from 'stream<string>' to 'string'. This can break existing clients.");
        warningMessages.add("warning: In 'Author' class 'bookNames' function definition return type has changed from " +
                "'stream<string>' to 'string'. This can break existing clients.");
        warningMessages.add("warning: In 'Author' service class 'bookNames' method changed from 'subscribe' to 'get'." +
                " This can break existing clients.");
        warningMessages.add("warning: In 'Book' class 'titles' function definition return type has changed from " +
                "'stream<string>' to 'string'. This can break existing clients.");
        warningMessages.add("warning: In 'Book' service class 'titles' method changed from 'subscribe' to 'get'. This" +
                " can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithRemovedDefaultValuesInInputAndObjectTypes() throws Exception {
        String newSchemaFileName = "SchemaWithRemovedDefaultValuesInInputAndObjectTypesApi.graphql";
        String beforeBalFileName = "typesBeforeRemovingDefaultValuesInInputAndObjectTypesRecordsAllowed.bal";
        String expectedBalFileName = "typesAfterRemovingDefaultValuesInInputAndObjectTypesRecordsAllowed.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setUseRecordsForObjects(true);
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> warningMessages = new ArrayList<>();
        warningMessages.add("warning: In 'CreateBookInput' record type 'title' field assigned '\"No title\"' default " +
                "value has removed. This can break existing clients.");
        warningMessages.add("warning: In 'CreateBookInput' record type 'price' field assigned '100.0' default value " +
                "has removed. This can break existing clients.");
        warningMessages.add("warning: In 'CreateBookInput' record type 'version' field assigned '\"v1.0\"' default " +
                "value has removed. This can break existing clients.");
        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == warningMessages.size());
        for (int i = 0; i < breakingChangeWarnings.size(); i++) {
            Assert.assertEquals(breakingChangeWarnings.get(i), warningMessages.get(i));
        }
    }

    @Test
    public void testCombiningUpdatedSchemaWithChangedDefaultValues() throws Exception {
        String newSchemaFileName = "SchemaWithChangedDefaultValuesApi.graphql";
        String beforeBalFileName = "typesBeforeChangingDefaultValues.bal";
        String expectedBalFileName = "typesAfterChangingDefaultValues.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaWithMetadataInModuleMembers() throws Exception {
        String newSchemaFileName = "SchemaWithMetadataInModuleMembersApi.graphql";
        String beforeBalFileName = "typesBeforeAddingMetadataInModuleMembers.bal";
        String expectedBalFileName = "typesAfterAddingMetadataInModuleMembers.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaIntoServiceObjectInTypesWithNonResolverFunctions() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewQueryFieldsApi.graphql";
        String beforeBalFileName =
                "typesServiceObjectWithNonResolverFunctionsBeforeAddingQueryFields.bal";
        String expectedBalFileName = "typesServiceObjectWithNonResolverFunctionsAfterAddingQueryFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaIntoServiceClassInTypesWithNonResolverFunctions() throws Exception {
        String beforeBalFileName =
                "typesServiceClassWithNonResolverFunctionsBeforeAddingQueryFields.bal";
        String newSchemaFileName = "SchemaWithAddedNewQueryFieldsApi.graphql";
        String expectedBalFileName = "typesServiceClassWithNonResolverFunctionsAfterAddingQueryFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaIntoServiceClassInTypesWithAttributes() throws Exception {
        String newSchemaFileName = "SchemaWithAddedNewQueryFieldsApi.graphql";
        String beforeBalFileName =
                "typesServiceClassWithAttributesBeforeAddingQueryFields.bal";
        String expectedBalFileName = "typesServiceClassWithAttributesAfterAddingQueryFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaIntoTypesWithIsolatedFunctions() throws Exception {
        String newSchemaFileName = "SchemaWithAddedQueryFieldsIntoTypesWithIsolatedFunctionsApi.graphql";
        String beforeBalFileName =
                "typesWithIsolatedFunctionsBeforeAddingQueryFields.bal";
        String expectedBalFileName = "typesWithIsolatedFunctionsAfterAddingQueryFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaIntoTypesWithDocumentations() throws Exception {
        String newSchemaFileName = "SchemaWithAddedObjectTypeFieldsIntoTypesWithDocumentationsApi.graphql";
        String beforeBalFileName =
                "typesWithDocumentationsBeforeAddingObjectTypeFields.bal";
        String expectedBalFileName = "typesWithDocumentationsAfterAddingObjectTypeFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaIntoTypesWithReadonlyObjectAndClass() throws Exception {
        String newSchemaFileName = "SchemaWithAddedQueryFieldsIntoTypesWithReadonlyObjectAndClassApi.graphql";
        String beforeBalFileName =
                "typesWithReadonlyObjectAndClassBeforeAddingQueryFields.bal";
        String expectedBalFileName = "typesWithReadonlyObjectAndClassAfterAddingObjectTypeFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }

    @Test
    public void testCombiningUpdatedSchemaIntoInputTypesWithReadonly() throws Exception {
        String newSchemaFileName = "SchemaWithAddedInputFieldsIntoTypesWithReadonlyInputTypesApi.graphql";
        String beforeBalFileName =
                "typesWithReadonlyInputTypesBeforeAddingInputFields.bal";
        String expectedBalFileName = "typesWithReadonlyInputTypesAfterAddingInputFields.bal";
        Path updatedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "updatedServices", beforeBalFileName));
        Path newSchemaPath = this.resourceDir.resolve(
                Paths.get("serviceGen", "graphqlSchemas", "updated", newSchemaFileName));
        Path mergedBalFilePath = this.resourceDir.resolve(
                Paths.get("serviceGen", "expectedServices", "updated", expectedBalFileName));

        GraphqlServiceProject newGraphqlProject =
                new GraphqlServiceProject(ROOT_PROJECT_NAME, newSchemaPath.toString(), "./");
        Utils.validateGraphqlProject(newGraphqlProject);

        String updatedBalFileContent = String.join(Constants.NEW_LINE, Files.readAllLines(updatedBalFilePath));
        ModulePartNode updateBalFileNode = NodeParser.parseModulePart(updatedBalFileContent);
        ServiceTypesGenerator serviceTypesGenerator = new ServiceTypesGenerator();
        serviceTypesGenerator.setFileName(newSchemaFileName.split("\\.")[0]);
        serviceTypesGenerator.setUseRecordsForObjects(true);
        ModulePartNode nextSchemaNode = serviceTypesGenerator.generateContentNode(newGraphqlProject.getGraphQLSchema());

        ServiceTypesFileCombiner serviceTypesFileCombiner =
                new ServiceTypesFileCombiner(updateBalFileNode, nextSchemaNode, newGraphqlProject.getGraphQLSchema());
        SyntaxTree mergedSyntaxTree = serviceTypesFileCombiner.generateMergedSyntaxTree();
        String result = Formatter.format(Formatter.format(mergedSyntaxTree).toString().trim()).trim();
        String expectedServiceTypesContent = readContentWithFormat(mergedBalFilePath);
        Assert.assertEquals(expectedServiceTypesContent, result);

        List<String> breakingChangeWarnings = serviceTypesFileCombiner.getBreakingChangeWarnings();
        Assert.assertTrue(breakingChangeWarnings.size() == 0);
    }
}
