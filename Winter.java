import java.util.*;

class Winter {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\033[0;35m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_YELLOW = "\033[0;33m";
    public static final String ANSI_BLUE = "\033[0;34m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\033[0;36m";
    Scanner in = new Scanner(System.in);
    Random random = new Random();
    int distance_objectif = 300;
    int distance_parcourue = 0;
    int distance_parcourue_temp;
    int vitesse = 100;
    int vitesse_malus_degats;
    int vitesse_malus_lieux;
    int vitesse_malus_periode;
    int precision = 100;
    int precision_malus_degats;
    int precision_malus_lieux;
    int precision_malus_periode;
    int obus_max = 85;
    int obus_HE = 8;
    int obus_HEAT = 25;
    int nombre_panne;
    int nombre_kit_max = 80;
    int nombre_kit = 20;
    int reponse;
    int reponse2;
    int variable_temp;
    int variable_temp2;
    int variable_temp3;
    int nombre_equipage = 5;
    int equipage = 5;
    int nombre_demijournee = 0;
    int flak = 0;
    int panther = 0;
    int tigre = 0;
    int jagdpanzer = 0;
    int nashorn = 0;
    boolean bflak = false;
    boolean bpanther = false;
    boolean btigre = false;
    boolean bjagdpanzer = false;
    boolean bnashorn = false;
    boolean vie_tank = false;
    boolean evenement = false;
    boolean jour = true;
    boolean plaine = true;
    boolean foret = false;
    boolean village = false;
    boolean bouclepanne = false;
    boolean panne = false;
    boolean debut = true;
    boolean partie = false;
    boolean menu = false;
    int[] pannetab = { 0, 0, 0 };

    void difficulte_base() {
        nombre_kit=20;
        distance_parcourue = 0;
        obus_max = 85;
        obus_HE = 10;
        obus_HEAT = 20;
        plaine = true;
        foret = false;
        village = false;
        flak = 0;
        panther = 0;
        tigre = 0;
        jagdpanzer = 0;
        nashorn = 0;
    }

    void choix_difficulte() {
        do {
            tank();
            System.out.println("============== [ " + ANSI_BLUE + "DIFFICULTE" + ANSI_RESET + " ] =============");
            System.out.println("1. Facile                       [ 300 km ]");
            System.out.println("2. Moyen                        [ 750 km ]");
            System.out.println("3. Lenine (Difficile)           [ 1 000 km ]");
            System.out.println("4. Staline (Impossible)         [ 1 500 km ]");
            System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
            reponse = in.nextInt();
            System.out.println("===========================================\n");
            switch (reponse) {
                case 1:
                    distance_objectif = 300;
                    break;
                case 2:
                    distance_objectif = 750;
                    plaine = false;
                    foret = true;
                    break;
                case 3:
                    distance_objectif = 1000;
                    plaine = false;
                    foret = true;
                    obus_max = 75;
                    obus_HE = 8;
                    obus_HEAT = 17;
                    break;
                case 4:
                    distance_objectif = 1500;
                    plaine = false;
                    village = true;
                    obus_max = 60;
                    obus_HE = 7;
                    obus_HEAT = 15;
                    break;
            }
        } while (reponse > 4 && reponse <= 0);
    }

    void tank() {
        System.out.println(ANSI_GREEN + "\n      .---.___,");
        System.out.println(" .--=='=='=-, '" + ANSI_RESET);
        System.out.println(ANSI_BLACK + "(O_o_o_o_o_O)" + ANSI_RESET);
    }

    void depart() {
        System.out.println("\n\n\n\n");
        tank();
        System.out.println("================= [ " + ANSI_RED + "WINTER 1944" + ANSI_RESET
                + " ] ============\n");
        System.out.println("Vous etes à bord d'un t-34-85 en hiver 1944.");
        System.out.println("Après avoir recu un ordre du commandement par radio,");
        System.out.println("vous devez progresser dans les lignes ennemis" + "\npour rejoindre une division blindé allié.");
        System.out.println("Votre objectif est de rejoindre \nla division alliée coute que coute.");
        System.out.println("\n==============================================\n");
    }

    void resultats() {
        tank();
        System.out.println("=============== [ " + ANSI_BLUE + "RESULTATS" + ANSI_RESET + " ] ================\n");
        if (distance_parcourue >= distance_objectif) {
            System.out.println("Bravo, vous avez réussi à rejoindre la division blindé !\n");
        } 
        else if (equipage == 0) {
            System.out.println("Vous avez perdu ! Vous et votre équipage etes tombé au front !\n");
        }
        else if (nombre_panne>19){
            System.out.println("Le tank a subit trop de panne. Abandon de la mission");
        }
        distance_parcourue = distance_objectif;
        System.out.println("La distance parcourue est de " + distance_parcourue + " km\n");
        System.out.println("Vous avez passé " + nombre_demijournee / 2 + " jours au combat\n");
        System.out.println("Vous avez éliminé :");
        System.out.println("        Panther    : " + panther);
        System.out.println("        Tigre      : " + tigre);
        System.out.println("        Nashorn    : " + nashorn);
        System.out.println("        Jagdpanzer : " + jagdpanzer);
        System.out.println("        FLAK 88    : " + flak);
        System.out.println("\n==============================================");
        partie = false;
    }

    void etat() {
        System.out.println("\n================== [ " + ANSI_BLUE + "ETAT" + ANSI_RESET + " ] ==================");
        System.out.println("[ " + ANSI_BLUE + "OBJECTIF" + ANSI_RESET + " ]");
        if (distance_parcourue >= distance_objectif) {
            System.out.println("        Distance parcourue : " + distance_objectif + "/" + distance_objectif + " km");
        } else {
            System.out.println("        Distance parcourue : " + distance_parcourue + "/" + distance_objectif + " km");
        }
        System.out.println("[ " + ANSI_BLUE + "VEHICULE" + ANSI_RESET + " ]");
        System.out.println("        Equiage : " + equipage + "/" + nombre_equipage);
        nombre_panne = 0;
        for (int i = 0; i < pannetab.length; i++) {
            nombre_panne += pannetab[i];
        }
        System.out.println("        Pannes : " + nombre_panne);
        panne();
        System.out.println("        KIT Réparation : " + nombre_kit + "/" + nombre_kit_max);
        System.out.println("[ " + ANSI_BLUE + "ENVIRONNEMENT" + ANSI_RESET + " ]");
        System.out.print("        Lieux : ");
        if (plaine) {
            System.out.println("Plaine");
            vitesse_malus_lieux = -10;
            precision_malus_lieux = -10;
        }
        if (foret) {
            System.out.println("Foret");
            vitesse_malus_lieux = 15;
            precision_malus_lieux = 25;
        }
        if (village) {
            System.out.println("Village");
            vitesse_malus_lieux = 40;
            precision_malus_lieux = 10;
        }

        System.out.print("        Période : ");
        if (nombre_demijournee == 0) {
            System.out.println("Matin du départ");
        } else if (jour) {
            System.out.println("Jour");
            vitesse_malus_periode = -5;
            precision_malus_periode = -5;
        } else {
            System.out.println("Nuit");
            vitesse_malus_periode = 25;
            precision_malus_periode = 30;
        }
        vitesse_malus_degats = pannetab[0] * 4 + pannetab[1] * 7;
        if (pannetab[0] == 0) {
            vitesse_malus_degats = vitesse_malus_degats + 3;
        }
        if (pannetab[1] == 0) {
            vitesse_malus_degats = vitesse_malus_degats + 3;
        }
        vitesse = vitesse - vitesse_malus_lieux - vitesse_malus_degats - vitesse_malus_periode;
        System.out.println("        Vitesse : " + vitesse + " %");
        System.out.println("        Jour : " + nombre_demijournee / 2);
        System.out.println("[ " + ANSI_BLUE + "ARMEMENT" + ANSI_RESET + " ]");
        precision_malus_degats = pannetab[2] * 4;
        if (pannetab[2] == 0) {
            precision_malus_degats = vitesse_malus_degats + 5;
        }
        precision();
        System.out.println("        Precision : " + precision + " %");
        System.out.println("        HEAT : " + obus_HEAT);
        System.out.println("        HE : " + obus_HE);
        System.out.println("        Place : " + (obus_max - (obus_HEAT + obus_HE)));
        System.out.println("==============================================");
    }

    void avancer() {
        if (evenement) {
            distance_parcourue_temp = vitesse / 8;
        } else {
            distance_parcourue_temp = vitesse / 5;
        }
    }

    void panne() {
        if (nombre_panne != 0) {
            for (int i = 0; i < pannetab.length; i++) {
                switch (i) {
                    case 0:
                        if (pannetab[i] > 0) {
                            System.out.println("        " + pannetab[i] + " Section(s) de chenille cassée(s)");
                        }
                        break;
                    case 1:
                        if (pannetab[i] > 0) {
                            System.out.println("        " + pannetab[i] + " Problème(s) moteur");
                        }
                        break;
                    case 2:
                        if (pannetab[i] > 0) {
                            System.out.println("        " + pannetab[i] + " Problème(s) à la tourelle");
                        }
                        break;
                }
            }
        }
    }

    void precision() {
        precision_malus_degats = pannetab[2] * 4;
        precision = precision - precision_malus_degats - precision_malus_lieux - precision_malus_periode;
        if (precision <= 20) {
            precision = 20;
        }
    }

    void reparation() { // PB QUAND ON REPARE
        if (nombre_panne != 0) {
            do {
                System.out.println(
                        "\n================== [ " + ANSI_BLUE + "REPARATION" + ANSI_RESET + " ] =============");
                variable_temp = 1;
                for (int i = 0; i < pannetab.length; i++) {
                    switch (i) {
                        case 0:
                            if (pannetab[i] >= 1) {
                                System.out.println(
                                        variable_temp + " : " + pannetab[i] + " section(s) de chenille cassée(s)");
                            }
                            break;
                        case 1:
                            if (pannetab[i] >= 1) {
                                System.out.println(variable_temp + " : " + pannetab[i] + " problème(s) moteur");
                            }
                            break;
                        case 2:
                            if (pannetab[i] >= 1) {
                                System.out.println(variable_temp + " : " + pannetab[i] + " problème(s) à la tourelle");
                            }
                            break;
                        default:
                    }
                    if (pannetab[i] >= 1) {
                        variable_temp++;
                    }
                }
                System.out.println("\n" + variable_temp + " : Quitter sans rien réparer");
                System.out.println("\nVous avez " + nombre_kit + " kits de réparations");
                System.out.print("\n" + ANSI_CYAN + "Choix de reparation : " + ANSI_RESET + "\n");
                reponse2 = in.nextInt();
            } while (reponse2 > variable_temp || reponse2 <= 0);
        } else {
            System.out.println("\n================== [ " + ANSI_BLUE + "REPARATION" + ANSI_RESET + " ] =============");
            System.out.println("Aucunne panne à signaler");
            System.out.println("===============================================\n");
        }

        if (nombre_panne != 0) {
            if (reponse2 != variable_temp && reponse2 < variable_temp) {
                variable_temp2 = 0;
                for (int i = 0; i < pannetab.length; i++) {
                    if (pannetab[i] >= 1) {
                        variable_temp2++;
                    }
                    if (variable_temp2 == reponse2) {
                        switch (i) {
                            case 0:
                                System.out.println("Combien de kit voulez vous utiliser sur les chennilles ? ");
                                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                                reponse2 = in.nextInt();
                                variable_temp3 = 0;
                                break;
                            case 1:
                                System.out.println("Combien de kit voulez vous utiliser sur le moteur ? ");
                                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                                reponse2 = in.nextInt();
                                variable_temp3 = 1;
                                break;
                            case 2:
                                System.out.println("Combien de kit voulez vous utiliser sur la tourelle ? ");
                                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                                reponse2 = in.nextInt();
                                variable_temp3 = 2;
                                break;
                        }
                        if (reponse2 > nombre_kit) {
                            System.out.println("Vous n'avez pas assez de kit");
                        } else if (reponse2 <= pannetab[i]) {
                            pannetab[i] = pannetab[i] - reponse2;
                            nombre_panne = nombre_panne - reponse2;
                            nombre_kit = nombre_kit - reponse2;
                            switch (variable_temp3) {
                                case 0:
                                    System.out
                                            .println("\nVous avez utilisé " + reponse2 + " kit(s) sur vos chenilles.");
                                    break;
                                case 1:
                                    System.out.println("\nVous avez utilisé " + reponse2 + " kit(s) sur le moteur.");
                                    break;
                                case 2:
                                    System.out.println("\nVous avez utilisé " + reponse2 + " kit(s) sur la tourelle.");
                                    break;
                            }
                        } else if (reponse2 > pannetab[i]) {
                            nombre_kit = nombre_kit - pannetab[variable_temp3];
                            nombre_panne = nombre_panne - (reponse2 - pannetab[variable_temp3]);
                            switch (variable_temp3) {
                                case 0:
                                    System.out.println(
                                            "Il y a " + pannetab[variable_temp3] + " sections de chenille cassée.");
                                    System.out.println("Elles ont été réparé en totalité, " + pannetab[variable_temp3]
                                            + " kit(s) utilisé(s)");
                                    break;
                                case 1:
                                    System.out.println("Il y a " + pannetab[variable_temp3] + " problème(s) moteur");
                                    System.out.println("Ils ont été réparé en totalité, " + pannetab[variable_temp3]
                                            + " kit(s) utilisé(s)");
                                    break;
                                case 2:
                                    System.out.println(
                                            "Il y a " + pannetab[variable_temp3] + " problème(s) à la tourelle");
                                    System.out.println("Ils ont été réparé en totalité, " + pannetab[variable_temp3]
                                            + " kit(s) utilisé(s)");
                                    break;
                            }
                            pannetab[variable_temp3] = 0;
                        }
                    }
                }
            }
            System.out.println("==============================================\n");
        }
    }

    void options() {
        System.out.println("\n================= [ " + ANSI_BLUE + "OPTIONS" + ANSI_RESET + " ] ================");
        System.out.println("1. Avancer");
        System.out.println("2. Inspecter les pannes");
        System.out.println("3. Reparer le véhicule");
        System.out.println("\n0. Menu");
        System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
        reponse = in.nextInt();
        System.out.println("==============================================\n");
        System.out.println("\n\n\n\n\n\n");
        if (reponse == 0) {
            menu = true;
        }
    }

    void reset() {
        precision = 100;
        vitesse = 100;
        distance_parcourue_temp = 0;
        variable_temp = 0;
        variable_temp2 = 0;
        variable_temp3 = 0;
        bouclepanne = false;
        evenement = false;
        vie_tank = false;
        bflak = false;
        bpanther = false;
        btigre = false;
        bjagdpanzer = false;
        bnashorn = false;
        menu = false;
    }

    void action() {
        switch (reponse) {
            case 1:
                avancer();
                distance_parcourue_temp += variable_temp;
                break;
            case 3:
                reparation();
                bouclepanne = true;
                break;
            case 2:
                System.out
                        .println("=============== [ " + ANSI_BLUE + "INSPECTION" + ANSI_RESET + " ] ================");
                variable_temp = 0;
                for (int i = 0; i < pannetab.length; i++) {
                    switch (i) {
                        case 0:
                            if (pannetab[i] > 0) {
                                System.out.println(+pannetab[i] + " Section(s) de chenille cassée(s). Malus Vitesse : "
                                        + (pannetab[0] * 4));
                            } else {
                                System.out.println("Aucunne panne sur les chenilles. Bonus Vitesse : 3");
                            }
                            break;
                        case 1:
                            if (pannetab[i] > 0) {
                                System.out.println(
                                        +pannetab[i] + " Problème(s) moteur. Malus Vitesse : " + (pannetab[1] * 7));
                            } else {
                                System.out.println("Aucunne panne moteur. Bonus Vitesse : 3");
                            }
                            break;
                        case 2:
                            if (pannetab[i] > 0) {
                                System.out.println(pannetab[i] + " Problème(s) à la tourelle. Malus Précision : "
                                        + (pannetab[2] * 4));
                            } else {
                                System.out.println("Aucunne sur la tourelle. Bonus Précision : 5");
                            }
                            break;
                    }
                    if (pannetab[i] >= 1) {
                        variable_temp++;
                    }
                }
                System.out.println("===============================================");
                bouclepanne = true;
                break;
        }
    }

    void loot() {
        do {
            System.out.println("1. Récuperer la maximum de tous");
            System.out.println("2. Récuperer des obus");
            System.out.println("3. Récuperer des kits");
            System.out.println("4. Ne rien récupérer");
            System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
            reponse2 = in.nextInt();
        } while (reponse2 > 4 && reponse <= 0);
        if (reponse2 == 1) {    ////// a recoder
            if ((variable_temp2 + variable_temp) < (obus_max - (obus_HE + obus_HEAT))) {
                System.out.println(
                        variable_temp + " obus HE sont récupérés et " + variable_temp2 + " obus HEAT sont récupérés");
                obus_HE += variable_temp;
                obus_HEAT += variable_temp2;
            } else {
                System.out.println("Vous n'avez pas assez de place pour prendre tout les obus");
                System.out.println("Combien d'obus HE voulez vous prendre ?");
                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                reponse2 = in.nextInt();
                if (reponse2 < (obus_max - (obus_HE + obus_HEAT))) {
                    obus_HE += reponse2;
                    System.out.println(reponse2 + " obus HE sont récupérés");
                }
                System.out.println("Combien d'obus HEAT voulez vous prendre ?");
                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                reponse2 = in.nextInt();
                if (reponse2 < (obus_max - (obus_HE + obus_HEAT))) {
                    obus_HEAT += reponse2;
                    System.out.println(reponse2 + " obus HEAT sont récupérés");
                }
            }
            if (variable_temp3 <= (nombre_kit_max - nombre_kit)) {
                nombre_kit += variable_temp3;
            } else {
                nombre_kit = nombre_kit_max;
            }
            System.out.println("Vous avez récupéré " + variable_temp3 + " kit de réparation");
        }
        if (reponse2 == 2) {
            System.out.println("Combien d'obus HE voulez vous prendre ?");
            System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
            reponse = in.nextInt();
            if (reponse < (obus_max - (obus_HE + obus_HEAT))) {
                obus_HE += variable_temp;
                System.out.println(variable_temp + " obus HE sont récupérés");
            }
            System.out.println("Combien d'obus HEAT voulez vous prendre ?");
            System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
            reponse = in.nextInt();
            if (reponse < (obus_max - (obus_HE + obus_HEAT))) {
                obus_HEAT += variable_temp2;
                System.out.println(variable_temp2 + " obus HEAT sont récupérés");
            }
        }
        if (reponse2 == 3) {}
            if (variable_temp3==0){
                System.out.println("Il n'y a plus de kit");
            }
            else if (nombre_kit>=nombre_kit_max){
                System.out.println("Vous ne pouvez pas prendre plus de kits de réparation.");
                System.out.println("Il n'y a plus de place dans le char.");
            }
            else {
                System.out.println("Combien de kit voulez vous prendre ?");
                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                reponse = in.nextInt();
                if (reponse >= variable_temp3){
                    if (variable_temp3+nombre_kit>=nombre_kit_max){
                        System.out.println("Vous avez récupéré " + (nombre_kit_max-nombre_kit) + " kit de réparation \n Il n'y a plus de place pour en stocker plus");
                        variable_temp3-=nombre_kit_max-nombre_kit;
                        nombre_kit=nombre_kit_max;
                    }
                    else {
                        System.out.println("Vous avez récupéré " + variable_temp3 + " kit de réparation");
                        nombre_kit+=variable_temp3;
                        variable_temp3-=variable_temp3;
                    }
                }
                else {
                    if(reponse+nombre_kit >= nombre_kit_max){
                        nombre_kit=nombre_kit_max;
                        System.out.println("Vous avez récupéré " + (nombre_kit_max-nombre_kit) + " kit de réparation \n Il n'y a plus de place pour en stocker plus");
                        variable_temp3-=reponse;
                    }
                    else{
                        nombre_kit=nombre_kit+reponse;
                        System.out.println("Vous avez récupéré " + reponse + " kit de réparation"); 
                        variable_temp3-=reponse;
                    }
                }
                System.out.println("\n");
            }
        if (reponse2 == 4) {
            System.out.println("Rien n'a été récupéré");
        }
        if (reponse2 != 2 && reponse2 != 4){
            loot();
        }
    }

    void combatroundcreux() {
        do {
            do {
                System.out.println("\nQuel type d'obus voulez vous utiliser pour tirer ?");
                System.out.println("1. Obus HE " + obus_HE);
                System.out.println("2. Obus HEAT " + obus_HEAT);
                System.out.println("3. Fuir le combat");
                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                reponse2 = in.nextInt();
                System.out.print("\n");
                if (reponse2 == 1) {
                    if (obus_HE == 0) {
                        System.out.println("Pas assez de munitions");
                        reponse2 = 3;
                    }
                }
                if (reponse2 == 1) {
                    if (obus_HE == 0) {
                        System.out.println("Pas assez de munitions");
                        reponse2 = 3;
                    }
                }
                if (reponse2 == 3) {
                    System.out.println("\nIl tire !");
                    variable_temp = random.nextInt(0, 101);
                    if (variable_temp <= 70) {
                        System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                        variable_temp = random.nextInt(0, 4);
                        switch (variable_temp) {
                            case 0:
                                pannetab[0] = pannetab[0] + 1;
                                System.out.println("Une chenille est touchée ! Elle est endommagée");
                                break;
                            case 1:
                                pannetab[1] = pannetab[1] + 1;
                                System.out.println("le moteur est touché ! Il est endommagé");
                                break;
                            case 2:
                                pannetab[2] = pannetab[2] + 1;
                                System.out.println("la tourelle est touchée ! Elle est endommagée");
                                break;
                            default: {
                                variable_temp = random.nextInt(0, 2);
                                if (variable_temp == 0) {
                                    System.out.println("L'obus à pénétré le blindage ! mais rien n'à été endommagé");
                                } else {
                                    System.out.println(
                                            "L'obus à pénétré à l'arrière ! Ca à l'air d'aller, rien de cassé");
                                }
                            }
                        }
                        variable_temp = random.nextInt(0, 5);
                        if (variable_temp == 0) {
                            System.out.println("Un membre d'équipage est touché ! Il est bléssé mais vivant");
                        }
                        if (variable_temp == 1) {
                            System.out.println("Un membre d'équipage est touché ! Il est mort !");
                            equipage += -1;
                        }
                    } else if (variable_temp > 70 && variable_temp < 80) {
                        System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a ricochet !");
                    } else if (variable_temp >= 80) {
                        System.out.println("Il a loupé son tir !");
                    }
                    vie_tank = false;
                }
            }

            while (reponse > 2 && reponse < 1);
            if (vie_tank) {
                if (reponse2 == 1) {
                    System.out.println("Vous tirez !");
                    obus_HE--;
                    precision();
                    variable_temp = random.nextInt(0, precision);
                    if (variable_temp <= (precision * 0.80)) {
                        System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a pénétré !");
                        variable_temp = random.nextInt(0, 2);
                        switch (variable_temp) {
                            case 1:
                                System.out.println("La canon s'aligne sur nous, il n'est pas détruit !");
                                break;
                            default:
                                System.out.println("Plus aucuns mouvement, il semble qu'il est détruit.");
                                vie_tank = false;
                                if (bflak) {
                                    bflak = false;
                                    flak++;
                                }
                                if (bnashorn) {
                                    bnashorn = false;
                                    nashorn++;
                                }
                        }
                    } else if (variable_temp > (precision * 0.80) && variable_temp < (precision * 0.90)) {
                        System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a ricochet !");
                    } else if (variable_temp >= (precision * 0.90)) {
                        System.out.println("L'obus n'a pas atteint la cible !");
                    }
                }
                if (reponse2 == 2) {
                    System.out.println("Vous tirez !");
                    obus_HEAT--;
                    precision();
                    variable_temp = random.nextInt(0, precision);
                    if (variable_temp <= (precision * 0.40)) {
                        System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a pénétré !");
                        variable_temp = random.nextInt(0, 3);
                        switch (variable_temp) {
                            case 1:
                                System.out.println("La tourelle s'aligne sur nous, il n'est pas détruit !");
                                break;
                            default:
                                System.out.println("Plus aucuns mouvement, il semble qu'il est détruit.");
                                vie_tank = false;
                                if (bflak) {
                                    bflak = false;
                                    flak++;
                                }
                                if (bnashorn) {
                                    bnashorn = false;
                                    nashorn++;
                                }
                        }
                    } else if (variable_temp > (precision * 0.40) && variable_temp < (precision * 0.70)) {
                        System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a ricochet !");
                    } else if (variable_temp >= (precision * 0.70)) {
                        System.out.println("L'obus n'a pas atteint la cible !");
                    }
                }
            }
            if (vie_tank) {
                System.out.println("\nIl tire !");
                variable_temp = random.nextInt(0, 101);
                if (variable_temp <= 70) {
                    System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                    variable_temp = random.nextInt(0, 4);
                    switch (variable_temp) {
                        case 0:
                            pannetab[0] = pannetab[0] + 1;
                            System.out.println("Une chenille est touchée ! Elle est endommagée");
                            break;
                        case 1:
                            pannetab[1] = pannetab[1] + 1;
                            System.out.println("le moteur est touché ! Il est endommagé");
                            break;
                        case 2:
                            pannetab[2] = pannetab[2] + 1;
                            System.out.println("la tourelle est touchée ! Elle est endommagée");
                            break;
                        default: {
                            variable_temp = random.nextInt(0, 2);
                            if (variable_temp == 0) {
                                System.out.println("L'obus à pénétré le blindage ! mais rien n'à été endommagé");
                            } else {
                                System.out.println("L'obus à pénétré à l'arrière ! Ca à l'air d'aller, rien de cassé");
                            }
                        }
                    }
                    variable_temp = random.nextInt(0, 5);
                    if (variable_temp == 0) {
                        System.out.println("Un membre d'équipage est touché ! Il est bléssé mais vivant");
                    }
                    if (variable_temp == 1) {
                        System.out.println("Un membre d'équipage est touché ! Il est mort !");
                        equipage--;
                    }
                } else if (variable_temp > 70 && variable_temp < 80) {
                    System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                    System.out.println("L'obus a ricochet !");
                } else if (variable_temp >= 80) {
                    System.out.println("Il a loupé son tir !");
                }
            }
        } while (equipage != 0 && vie_tank);
    }

    void combatroundplein() {
        do {
            do {
                System.out.println("\nQuel type d'obus voulez vous utiliser pour tirer ?");
                System.out.println("1. Obus HE " + obus_HE);
                System.out.println("2. Obus HEAT " + obus_HEAT);
                System.out.println("3. Fuir le combat");
                System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
                reponse2 = in.nextInt();
                System.out.print("\n");
                if (reponse2 == 1) {
                    if (obus_HE == 0) {
                        System.out.println("Pas assez de munitions");
                        reponse2 = 3;
                    }
                }
                if (reponse2 == 1) {
                    if (obus_HE == 0) {
                        System.out.println("Pas assez de munitions");
                        reponse2 = 3;
                    }
                }
                if (reponse2 == 3) {
                    System.out.println("\nIl tire !");
                    variable_temp = random.nextInt(0, 101);
                    if (variable_temp <= 70) {
                        System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                        variable_temp = random.nextInt(0, 4);
                        switch (variable_temp) {
                            case 0:
                                pannetab[0] = pannetab[0] + 1;
                                System.out.println("Une chenille est touchée ! Elle est endommagée");
                                break;
                            case 1:
                                pannetab[1] = pannetab[1] + 1;
                                System.out.println("le moteur est touché ! Il est endommagé");
                                break;
                            case 2:
                                pannetab[2] = pannetab[2] + 1;
                                System.out.println("la tourelle est touchée ! Elle est endommagée");
                                break;
                            default: {
                                variable_temp = random.nextInt(0, 2);
                                if (variable_temp == 0) {
                                    System.out.println("L'obus à pénétré le blindage ! mais rien n'à été endommagé");
                                } else {
                                    System.out.println(
                                            "L'obus à pénétré à l'arrière ! Ca à l'air d'aller, rien de cassé");
                                }
                            }
                        }
                        variable_temp = random.nextInt(0, 5);
                        if (variable_temp == 0) {
                            System.out.println("Un membre d'équipage est touché ! Il est bléssé mais vivant");
                        }
                        if (variable_temp == 1) {
                            System.out.println("Un membre d'équipage est touché ! Il est mort !");
                            equipage--;
                        }
                    } else if (variable_temp > 70 && variable_temp < 80) {
                        System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a ricochet !");
                    } else if (variable_temp >= 80) {
                        System.out.println("Il a loupé son tir !");
                    }
                    vie_tank = false;
                }
            } while (reponse > 2 && reponse < 1);
            if (vie_tank) {
                if (reponse2 == 1) {
                    System.out.println("Vous tirez !");
                    obus_HE--;
                    precision();
                    variable_temp = random.nextInt(0, precision);
                    if (variable_temp <= (precision * 0.40)) {
                        System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a pénétré !");
                        variable_temp = random.nextInt(0, 2);
                        switch (variable_temp) {
                            case 1:
                                System.out.println("La tourelle s'aligne sur nous, il n'est pas détruit !");
                                break;
                            default:
                                System.out.println("Plus aucuns mouvement, il semble qu'il est détruit.");
                                vie_tank = false;
                                if (bpanther) {
                                    bpanther = false;
                                    panther++;
                                }
                                if (btigre) {
                                    btigre = false;
                                    tigre++;
                                }
                                if (bjagdpanzer) {
                                    bjagdpanzer = false;
                                    jagdpanzer++;
                                }
                        }
                    } else if (variable_temp > (precision * 0.40) && variable_temp < (precision * 0.70)) {
                        System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a ricochet !");
                    } else if (variable_temp >= (precision * 0.70)) {
                        System.out.println("L'obus n'a pas atteint la cible !");
                    }
                }
                if (reponse2 == 2) {
                    System.out.println("Vous tirez !");
                    obus_HEAT--;
                    precision();
                    variable_temp = random.nextInt(0, precision);
                    if (variable_temp <= (precision * 0.80)) {
                        System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a pénétré !");
                        variable_temp = random.nextInt(0, 3);
                        switch (variable_temp) {
                            case 1:
                                System.out.println("La tourelle s'aligne sur nous, il n'est pas détruit !");
                                break;
                            default:
                                System.out.println("Plus aucuns mouvement, il semble qu'il est détruit.");
                                vie_tank = false;
                                if (bpanther) {
                                    bpanther = false;
                                    panther++;
                                }
                                if (btigre) {
                                    btigre = false;
                                    tigre++;
                                }
                                if (bjagdpanzer) {
                                    bjagdpanzer = false;
                                    jagdpanzer++;
                                }
                        }
                    } else if (variable_temp > (precision * 0.80) && variable_temp < (precision * 0.90)) {
                        System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                        System.out.println("L'obus a ricochet !");
                    } else if (variable_temp >= (precision * 0.90)) {
                        System.out.println("L'obus n'a pas atteint la cible !");
                    }
                }
            }
            if (vie_tank) {
                System.out.println("\nIl tire !");
                variable_temp = random.nextInt(0, 101);
                if (variable_temp <= 70) {
                    System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                    variable_temp = random.nextInt(0, 4);
                    switch (variable_temp) {
                        case 0:
                            pannetab[0] = pannetab[0] + 1;
                            System.out.println("Une chenille est touchée ! Elle est endommagée");
                            break;
                        case 1:
                            pannetab[1] = pannetab[1] + 1;
                            System.out.println("le moteur est touché ! Il est endommagé");
                            break;
                        case 2:
                            pannetab[2] = pannetab[2] + 1;
                            System.out.println("la tourelle est touchée ! Elle est endommagée");
                            break;
                        default: {
                            variable_temp = random.nextInt(0, 2);
                            if (variable_temp == 0) {
                                System.out.println("L'obus à pénétré le blindage ! mais rien n'à été endommagé");
                            } else {
                                System.out.println("L'obus à pénétré à l'arrière ! Ca à l'air d'aller, rien de cassé");
                            }
                        }
                    }
                    variable_temp = random.nextInt(0, 5);
                    if (variable_temp == 0) {
                        System.out.println("Un membre d'équipage est touché ! Il est bléssé mais vivant");
                    }
                    if (variable_temp == 1) {
                        System.out.println("Un membre d'équipage est touché ! Il est mort !");
                        equipage--;
                    }
                } else if (variable_temp > 70 && variable_temp < 80) {
                    System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                    System.out.println("L'obus a ricochet !");
                } else if (variable_temp >= 80) {
                    System.out.println("Il a loupé son tir !");
                }
            }
        } while (equipage != 0 && vie_tank);
    }

    void combatbase() {
        variable_temp = random.nextInt(0, 3);
        if (variable_temp == 0) {
            System.out.println(" Sur la droite !");
        }
        if (variable_temp == 1) {
            System.out.println(" Sur la gauche !");
        }
        if (village) {
            System.out.println("\nIl tire !");
            variable_temp = random.nextInt(0, 101);
            if (variable_temp <= 70) {
                System.out.println(ANSI_RED + "H I T" + ANSI_RESET);
                variable_temp = random.nextInt(0, 3);
                switch (variable_temp) {
                    case 0:
                        pannetab[0] = pannetab[0] + 1;
                        System.out.println("Une chenille est touchée ! Elle est endommagée");
                        break;
                    case 1:
                        pannetab[1] = pannetab[1] + 1;
                        System.out.println("le moteur est touché ! Il est endommagé");
                        break;
                    case 2:
                        pannetab[2] = pannetab[2] + 1;
                        System.out.println("la tourelle est touchée ! Elle est endommagée");
                        break;
                    default: {
                        variable_temp = random.nextInt(0, 2);
                        if (variable_temp == 0) {
                            System.out.println("L'obus à pénétré le blindage ! mais rien n'à été endommagé");
                        } else {
                            System.out.println(
                                    "L'obus à pénétré à l'arrière ! Ca à l'air d'aller, rien de cassé");
                        }
                    }
                }
                variable_temp = random.nextInt(0, 5);
                if (variable_temp == 0) {
                    System.out.println("Un membre d'équipage est touché ! Il est bléssé mais vivant");
                }
                if (variable_temp == 1) {
                    System.out.println("Un membre d'équipage est touché ! Il est mort !");
                    equipage--;
                }
            } else if (variable_temp > 70 && variable_temp < 80) {
                System.out.println(ANSI_YELLOW + "H I T" + ANSI_RESET);
                System.out.println("L'obus a ricochet !");
            } else if (variable_temp >= 80) {
                System.out.println("Il a loupé son tir !");
            }
        }
    }

    void combat() {
        switch (variable_temp) {
            case 6, 7, 8: // panther
                evenement = true;
                bpanther = true;
                etat();
                System.out
                        .println("\n================== [ " + ANSI_RED + "COMBAT" + ANSI_RESET + " ] ================");
                vie_tank = true;
                System.out.print("Un " + ANSI_RED + "Panther" + ANSI_RESET + " !");
                combatbase();
                combatroundplein();
                System.out.println("==============================================\n");
                break;
            case 9, 10: // tigre
                evenement = true;
                btigre = true;
                etat();
                System.out
                        .println("\n================== [ " + ANSI_RED + "COMBAT" + ANSI_RESET + " ] ================");
                vie_tank = true;
                System.out.print("Un " + ANSI_RED + "Tigre" + ANSI_RESET + " !");
                combatbase();
                combatroundplein();
                System.out.println("==============================================\n");
                break;
            case 11, 12: // jag
                evenement = true;
                bjagdpanzer = true;
                etat();
                System.out
                        .println("\n================== [ " + ANSI_RED + "COMBAT" + ANSI_RESET + " ] ================");
                vie_tank = true;
                System.out.print("Un " + ANSI_RED + "Jagdpanzer" + ANSI_RESET + " !");
                combatbase();
                combatroundplein();
                System.out.println("==============================================\n");
                break;
            case 13, 14: // nashorn
                evenement = true;
                bnashorn = true;
                etat();
                System.out
                        .println("\n================== [ " + ANSI_RED + "COMBAT" + ANSI_RESET + " ] ================");
                vie_tank = true;
                System.out.print("Un " + ANSI_RED + "Nashorn" + ANSI_RESET + " !");
                combatbase();
                combatroundcreux();
                System.out.println("==============================================\n");
                break;
            case 15, 16: // flak
                evenement = true;
                bflak = true;
                etat();
                System.out
                        .println("\n================== [ " + ANSI_RED + "COMBAT" + ANSI_RESET + " ] ================");
                vie_tank = true;
                System.out.print("Un " + ANSI_RED + "Flak 88" + ANSI_RESET + " !");
                combatbase();
                combatroundcreux();
                System.out.println("==============================================\n");
                break;
            default:
                if (village) {
                    switch (variable_temp) {
                        case 17: // tigre
                            evenement = true;
                            btigre = true;
                            etat();
                            System.out.println("\n================== [ " + ANSI_RED + "COMBAT" + ANSI_RESET
                                    + " ] ================");
                            vie_tank = true;
                            System.out.print("Un " + ANSI_RED + "Tigre" + ANSI_RESET + " !");
                            combatbase();
                            combatroundplein();
                            System.out.println("==============================================\n");
                            break;
                        case 18: // panther
                            evenement = true;
                            bpanther = true;
                            etat();
                            System.out.println("\n================== [ " + ANSI_RED + "COMBAT" + ANSI_RESET
                                    + " ] ================");
                            vie_tank = true;
                            System.out.print("Un " + ANSI_RED + "Jagdpanzer" + ANSI_RESET + " !");
                            combatbase();
                            combatroundplein();
                            System.out.println("==============================================\n");
                            break;
                    }
                }
        }
    }

    void run() {
        do {
            if (!bouclepanne && !evenement && !menu) {
                if ((nombre_demijournee % 2) == 1) {
                    jour = true;
                } else {
                    jour = false;
                }
                nombre_demijournee++;
                variable_temp = random.nextInt(1, 7);
                switch (variable_temp) {
                    case 1, 2, 3:
                        plaine = true;
                        foret = false;
                        village = false;
                        break;
                    case 4, 5:
                        plaine = false;
                        foret = true;
                        village = false;
                        break;
                    case 6:
                        plaine = false;
                        foret = false;
                        village = true;
                        break;
                }
            }
            if (!bouclepanne && !evenement && !debut) {
                variable_temp = random.nextInt(0, 45);
                switch (variable_temp) {
                    case 0, 1:
                        System.out.println("================== [ " + ANSI_YELLOW + "ACTION" + ANSI_RESET
                                + " ] ================\n");
                        System.out.println("Vous etes tombé sur un tank allié endommagé, vous décidez de le fouiller");
                        System.out.println("En fouillant le véhicule vous trouvez :");
                        variable_temp = random.nextInt(1, 3);
                        if (variable_temp == 1) {
                            System.out.println("        Un membre d'équipage");
                            if (equipage < nombre_equipage) {
                                System.out.println("Vous l'acceuillez dans votre véhicule");
                                equipage++;
                            } else if (equipage == 5) {
                                System.out.println("Le tank étant déjà à pleine capacité, vous ne pouvez l'acceuillir");
                            }
                        }
                        variable_temp = random.nextInt(2, 5);
                        System.out.println("        " + variable_temp + " Obus HE");
                        variable_temp2 = random.nextInt(3, 9);
                        System.out.println("        " + variable_temp2 + " Obus HEAT");
                        variable_temp3 = random.nextInt(3, 10);
                        System.out.println("        " + variable_temp3 + " Kit de réparation");
                        loot();
                        System.out.println("==============================================");
                        evenement = true;
                        break;
                    case 3:
                        System.out.println("================== [ " + ANSI_YELLOW + "ACTION" + ANSI_RESET
                                + " ] ================\n");
                        System.out.println(
                                "Vous etes tombé sur un tank énnemi endommagé abandonné, vous décidez de le fouiller");
                        System.out.println("En fouillant le véhicule vous trouvez :");
                        variable_temp = random.nextInt(2, 4);
                        System.out.println("        " + variable_temp + " Obus HE");
                        variable_temp2 = random.nextInt(2, 8);
                        System.out.println("        " + variable_temp2 + " Obus HEAT");
                        variable_temp3 = random.nextInt(3, 8);
                        System.out.println("        " + variable_temp3 + " Kit de réparation");
                        loot();
                        System.out.println("==============================================");
                        evenement = true;
                        break;
                    case 5:
                        variable_temp = random.nextInt(1, 5);
                        if (variable_temp == 2) {
                            System.out.println("================== [ " + ANSI_PURPLE + "=^..^=" + ANSI_RESET
                                    + " ] ================\n");
                            System.out.println(ANSI_PURPLE + "=^..^=");
                            System.out.println("meooow");
                            System.out.println("pôtit chat " + ANSI_RESET);
                            distance_parcourue += -5;
                            System.out.println("\n==============================================");
                        }
                        break;
                    case 29:
                        System.out.println("================== [ " + ANSI_YELLOW + "ACTION" + ANSI_RESET
                                + " ] ================\n");
                        System.out.println("Le moteur surchauffe ! Il a subit des degats");
                        System.out.println("Vous devez le reparer !");
                        pannetab[1] = pannetab[1] + 1;
                        System.out.println("\n==============================================");
                        break;
                    case 30:
                        System.out.println("================== [ " + ANSI_YELLOW + "ACTION" + ANSI_RESET
                                + " ] ================\n");
                        System.out.println("Une section de chenille s'est rompue ! ");
                        System.out.println("Vous devez le reparer !");
                        pannetab[0] = pannetab[0] + 1;
                        System.out.println("\n==============================================");
                    case 31:
                        System.out.println("================== [ " + ANSI_YELLOW + "ACTION" + ANSI_RESET
                                + " ] ================\n");
                        System.out.println("Vous trouvez un groupe de soldat russe");
                        System.out.println("L'un d'eux est un membre d'équipage de t-34 abbatu");
                        if (equipage < nombre_equipage) {
                            System.out.println("Il se propose pour monter avec vous, vous l'embarquez.");
                            equipage++;
                        } else if (equipage == 5) {
                            System.out.println(
                                    "Il se propose pour monter, mais le tank étant déjà à pleine capacité, vous ne pouvez l'acceuillir");
                        }
                        System.out.println("\n==============================================");
                    default:
                        combat();
                }
            }
            reset();
            etat();
            options();
            action();
            if (!bouclepanne && !evenement && !menu) {
                System.out.println(
                        "================== [ " + ANSI_YELLOW + "ACTION" + ANSI_RESET + " ] ================\n");
                distance_parcourue += distance_parcourue_temp;
                if (distance_parcourue_temp == 0) {
                    System.out.println("Vous avez parcouru " + ANSI_RED + "0 km " + ANSI_RESET + "cette demi-journée");
                } else {
                    System.out.println("Vous avez parcouru " + distance_parcourue_temp + " km cette demi-journée\n");
                    System.out.println("==============================================\n");
                }
            }
            debut = false;
        } while (reponse != 0 && nombre_panne<=19 && equipage != 0 && distance_parcourue < distance_objectif);
        if (!menu) {
            resultats();
        }
        menu = false;
        menu();
    }

    void info(){
        tank();
        System.out.println("\n================== [ " + ANSI_BLUE + "INFO" + ANSI_RESET + " ] ==================");
        System.out.println("WYBON VICTOR 2023 | 2024");
        System.out.println("IUT LENS INFORMATIQUE");
        System.out.print("BUT 1 A2");
        System.out.println("\n==============================================");
    }

    void aide(){
        tank();
        System.out.println("Bienvenue dans WINTER1944 !");
        System.out.println("Vous etes à bord d'un t-34-85 en hiver 1944.");
        System.out.println("Après avoir recu un ordre du commandement par radio,");
        System.out.println("vous devez progresser dans les lignes ennemis" + "\npour rejoindre une division blindé allié.");
        System.out.println("Votre objectif est de rejoindre \nla division alliée coute que coute.");
        System.out.println("\n================== [ " + ANSI_BLUE + "AIDES" + ANSI_RESET + " ] =================");
        System.out.println("Afin de détruire les unités énemies, essayer d'utiliser les munitions adaptées.\n");
        System.out.println("Utilisez les obus HE sur les tank ouvert et peu blindé.");
        System.out.println("Pour les tanks plus blindé, il faut utiliser des obus a coiffe perforante, HEAT.");
        System.out.println("\n==============================================");
    }

    void menu() {
        do {
            System.out.println("\n================== [ " + ANSI_BLUE + "MENU" + ANSI_RESET + " ] ==================");
            System.out.println("1. Nouvelle partie");
            System.out.println("2. Aides");
            System.out.println("3. Informations");
            if (partie) {
                System.out.println("4. Continuer");
            }
            System.out.println("0. Quitter le jeu  (w/o save)");
            System.out.print("\n" + ANSI_CYAN + "Choix : " + ANSI_RESET);
            reponse = in.nextInt();
            System.out.println("==============================================");
            if (reponse == 3) {
                System.out.println("\n\n\n\n\n\n");
                info();
                menu();
            }
            if (reponse == 2) {
                aide();
                menu();
            }
            if (reponse == 1) {
                System.out.println("\n\n\n\n\n\n");
                partie = true;
                difficulte_base();
                choix_difficulte();
                depart();
                run();
            } else if (reponse == 0) {
                System.out.println("\n\n\n============ [ " + ANSI_BLUE + "FERMETURE DU JEU" + ANSI_RESET
                        + " ] ============\n\n\n");
            } else if (reponse == 4) {
                System.out.println("\n\n\n\n\n\n");
                run();
            }
        } while (reponse > 4 && reponse < 0);
    }

    public static void main(String[] args) {
        new Winter().menu();
    }
}