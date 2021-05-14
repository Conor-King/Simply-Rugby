package simplyRugby;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * SkillCategory class: <br>
 * The skill category class holds the name and skills tied to the category. <br>
 * Implements {@link Serializable}.
 * @author Conor King
 * @date 10/05/2021
 */
public class SkillCategory implements Serializable {

    // Attributes.
    private String skillCategory;
    private ArrayList<Skill> skills;

    /**
     * The constructor for the skill category class. <br>
     * Instantiates a new Skill category.
     * @param skillCategory A string skill category name
     * @param skills        Arraylist of skills
     */
    public SkillCategory(String skillCategory, ArrayList<Skill> skills) {
        this.skillCategory = skillCategory;
        this.skills = skills;
    }

    /**
     * Gets skill category.
     * @return A string skill category name
     */
    public String getSkillCategory() {
        return skillCategory;
    }

    /**
     * Gets skills.
     * @return Arraylist of skills
     */
    public ArrayList<Skill> getSkills() {
        return skills;
    }

    /**
     * Add skill string. <br>
     * Adds a skill to the skill category. <br>
     * Used when adding to player skill categories.
     * @param skill  Skill instance skill
     * @param rating A integer rating
     * @return A string with the result of the addition.
     */
    public String addSkill(Skill skill, int rating) {

        for (Skill skill1: skills) {
            if (skill1.getName().equals(skill.getName())) {
                return "Skill " + skill1.getName().toLowerCase() + " already exists!";
            }
        }

        LocalDate now = LocalDate.now();
        Skill skill1 = new Skill(skill.getName(), skill.getDescription(), rating, now);
        skills.add(skill1);
        return "Skill " + skill1.getName().toLowerCase() + " successfully added to skill category " + this.getSkillCategory().toLowerCase() + "!";
    }

}
