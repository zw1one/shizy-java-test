package com.shizy.happy.fight.role;

public class RoleBase {

    public String role;
    public String name;

    public int maxHp;
    public int hp;
    public int maxMp;
    public int mp;

    public int attck;
    public int defense;

    public void showRoleState() {
        System.out.println("|--" + name + " state--|");

        System.out.println("role: " + role);
        System.out.println("hp: " + hp + "/" + maxHp);
        System.out.println("mp: " + mp + "/" + maxMp);
//        System.out.println("attck: " + attck);
//        System.out.println("defense: " + defense);

        System.out.println("--------------");
    }
}
