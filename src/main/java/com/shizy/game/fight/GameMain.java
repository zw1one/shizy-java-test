package com.shizy.game.fight;

import com.shizy.game.fight.menu.FightInfo;
import com.shizy.game.fight.menu.FightMenu;
import com.shizy.game.fight.role.Goblin;
import com.shizy.game.fight.role.RoleBase;
import com.shizy.game.fight.role.Swordman;

import java.util.ArrayList;
import java.util.List;

public class GameMain {

    public static void main(String[] args) throws Exception {
        new GameMain().start();
    }

    public void start() throws Exception {

        FightMenu fightMenu = new FightMenu();
        FightInfo fightInfo = new FightInfo();

        RoleBase swordman = new Swordman();
        swordman.name = "王哥";
        RoleBase goblin = new Goblin();
        goblin.name = "杂鱼A";

        fightInfo.setYou(swordman);
        List enemy = new ArrayList<>();
        enemy.add(goblin);
        fightInfo.setEnemy(enemy);

        fightMenu.initBattleground(fightInfo);

        while (true) {

            System.out.println("You turn.");
            Thread.sleep(1000);

            //showRoleState
            System.out.println("State:");
            swordman.showRoleState();
            goblin.showRoleState();

            //fightMenu
            String fightMenuCmd = fightMenu.showMenu();
            fightMenu.receiveCmd(fightMenuCmd);

            //aiAction
            System.out.println("aiAction");
            Thread.sleep(2000);
            System.out.println("aiAction end");

            //exit
            if(enemy.size() == 0){
                System.out.println("clear!");
                return;
            }

        }

    }


}
















