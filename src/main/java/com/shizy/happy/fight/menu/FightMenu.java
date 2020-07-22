package com.shizy.happy.fight.menu;

import com.shizy.happy.fight.role.RoleBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FightMenu {

    public static List menuList;

    Scanner sc = new Scanner(System.in);

    static {
        menuList = new ArrayList();
        menuList.add("attack");
//        menuList.add("defense");
//        menuList.add("skill");
//        menuList.add("item");
//        menuList.add("escape");
    }

    public String showMenu() {
        System.out.println("Action:");
        System.out.println("==============");
        for (int i = 0; i < menuList.size(); i++) {
            System.out.println((i + 1) + "." + menuList.get(i));
        }
        System.out.println("==============");
        System.out.print("input cmd: ");
        String fightMenuCmd = sc.nextLine();
        return fightMenuCmd;
    }

    private FightInfo fightInfo;

    public void initBattleground(FightInfo fightInfo) {
        this.fightInfo = fightInfo;
    }


    public void receiveCmd(String cmd) throws Exception {
        System.out.println("--");
        String cmdStr = (String) menuList.get(Integer.parseInt(cmd) - 1);
        this.getClass().getMethod(cmdStr).invoke(this);
    }

    public void attack() throws Exception {

        //select target

        RoleBase you = fightInfo.getYou();
        List<RoleBase> enemy = fightInfo.getEnemy();

        System.out.println("Enemy:");
        System.out.println("==============");
        for (int i = 0; i < enemy.size(); i++) {
            System.out.println((i + 1) + "." + enemy.get(i).name);
        }
        System.out.println("==============");
        System.out.print("select target: ");
        String select = sc.nextLine();

        RoleBase target = enemy.get(Integer.parseInt(select) - 1);

        //attack calculate

        int damage = you.attck - target.defense;
        target.hp = target.hp - damage;
        Thread.sleep(500);
        System.out.println(you.name + " attacking " + target.name + ". Cause " + damage + " damage.");
        Thread.sleep(1000);

        if (target.hp < 0) {
            target.hp = 0;
        }

        if (target.hp == 0) {
            System.out.println(target.name + "dead.");
            enemy.remove(target);
        }


    }


}















