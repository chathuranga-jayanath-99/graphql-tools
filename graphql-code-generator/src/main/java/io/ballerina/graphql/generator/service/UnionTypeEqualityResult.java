package io.ballerina.graphql.generator.service;

import io.ballerina.compiler.syntax.tree.UnionTypeDescriptorNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.ballerina.graphql.generator.service.BaseCombiner.getTypeName;

/**
 * Utility class to store result comparing union types.
 */
public class UnionTypeEqualityResult {
    private List<String> addedUnionMembers;
    private List<String> removedUnionMembers;
    private UnionTypeDescriptorNode prevUnionType;
    private UnionTypeDescriptorNode nextUnionType;
    private List<String> prevUnionMembers;
    private List<String> nextUnionMembers;

    public UnionTypeEqualityResult(UnionTypeDescriptorNode prevUnionType, UnionTypeDescriptorNode nextUnionType) {
        this.prevUnionType = prevUnionType;
        this.nextUnionType = nextUnionType;
        prevUnionMembers = new ArrayList<>();
        nextUnionMembers = new ArrayList<>();
        removedUnionMembers = new ArrayList<>();
        addedUnionMembers = new ArrayList<>();
        populateUnionMemberNames(prevUnionType, prevUnionMembers);
        populateUnionMemberNames(nextUnionType, nextUnionMembers);
    }

    public UnionTypeEqualityResult() {
        addedUnionMembers = new ArrayList<>();
        removedUnionMembers = new ArrayList<>();
    }

    public void separateMembers() {
        HashMap<String, Boolean> nextUnionMemberAvailability = new HashMap<>();
        for (String nextUnionTypeMember : nextUnionMembers) {
            nextUnionMemberAvailability.put(nextUnionTypeMember, false);
        }
        for (String prevUnionTypeMember : prevUnionMembers) {
            boolean foundMatch = false;
            for (String nextUnionTypeMember : nextUnionMembers) {
                if (prevUnionTypeMember.equals(nextUnionTypeMember)) {
                    foundMatch = true;
                    nextUnionMemberAvailability.put(nextUnionTypeMember, true);
                    break;
                }
            }
            if (!foundMatch) {
                removedUnionMembers.add(prevUnionTypeMember);
            }
        }
        for (Map.Entry<String, Boolean> availableEntry : nextUnionMemberAvailability.entrySet()) {
            Boolean nextUnionMemberAvailable = availableEntry.getValue();
            if (!nextUnionMemberAvailable) {
                String notAvailableNextUnionMember = availableEntry.getKey();
                addedUnionMembers.add(notAvailableNextUnionMember);
            }
        }
    }

    public UnionTypeDescriptorNode generateCombinedUnionType() {
        return nextUnionType;
    }

    public void addToAdditions(String addition) {
        addedUnionMembers.add(addition);
    }

    public void addToRemovals(String removal) {
        removedUnionMembers.add(removal);
    }

    public boolean isEqual() {
        return addedUnionMembers.isEmpty() && removedUnionMembers.isEmpty();
    }

    public List<String> getRemovedUnionMembers() {
        return removedUnionMembers;
    }

    private void populateUnionMemberNames(UnionTypeDescriptorNode unionType, List<String> unionTypeMembers) {
        unionTypeMembers.add(getTypeName(unionType.rightTypeDesc()));
        if (unionType.leftTypeDesc() instanceof UnionTypeDescriptorNode) {
            UnionTypeDescriptorNode leftUnionType = (UnionTypeDescriptorNode) unionType.leftTypeDesc();
            populateUnionMemberNames(leftUnionType, unionTypeMembers);
        } else {
            unionTypeMembers.add(getTypeName(unionType.leftTypeDesc()));
        }
    }
}
