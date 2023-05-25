package accounting;
import java.io.BufferedWriter; //kwandika facture
import java.io.FileWriter; //kwandika facture
import java.io.IOException; // exception
import java.util.*; // umwanya na scanner
import java.text.*; //umwanya


class account { 
	int account_no;
	String name;
	String acc;
	String fourn;
	int money;
	int code;
	int amount;
	int credit;
	int choix;
	int cr;
	int tr;
	int tunes;
	int prevTrans;
	boolean r;
	int flag;
	Scanner qw = new Scanner(System.in);
	public account() {
		this(" ", 0);
	}
	public account(String j, int id) {
		name = j;
		account_no = id;
	}


	void insert() {
		Scanner in = new Scanner(System.in);
		System.out.println("Entrez le numero de serie: ");
		account_no = in.nextInt();
		System.out.println("Entrez votre nom: ");
		name = in.next();
		System.out.println("Entrez le montant: ");
		amount = in.nextInt();
	}

	void deposit() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Quel montant voulez vous ajouter?  ");
		int amt = sc.nextInt();
		amount += amt;
		prevTrans = amt;
		System.out.println(amt + "bif  deposé\n");
	}

	void withdrawn() {
		Scanner out = new Scanner(System.in);
		System.out.print("Quel montant voulez vous retirer? ");
		int amt = out.nextInt();
		if (amount < amt) {
			System.out.println("Balance insuffisant");
		} else {
			amount -= amt;
			System.out.println(amt + "bif  retiré");
			prevTrans = -amt;
		}
	}

	void checkbalance() {
		System.out.println(">>Votre Balance est de  " + amount + " bif");
	}

	void checktunes() {
		if(tunes>0)
		{
		    System.out.println(">>Votre crédit est de " + tunes);
		}
		else
		{
		    System.out.println(">>Votre Compte est insuffisant\nRecharger pour plus de services.");
		}  
	}

	void display() {
		System.out.println(account_no + " " + name + " a " + amount + " bif");
	}
	void time() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		System.out.println(formatter.format(date));
	}
	void quitter() {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();

			BufferedWriter writer = new BufferedWriter(new FileWriter("/storage/emulated/0/Download/Facture/Facture.txt"));
			writer.write("***FACTURE***\n");
			writer.write("--------------\n");
			writer.write("Num." + account_no + "\nNom: " + name + "\nVotre solde est de " + amount + " bif\n");
			if (cr != 0) {
				writer.write("\nCrédit de " + credit + " bif dont l'Interet de " + cr / 10 + " bif inclus\n");
			}
			writer.write("\nMerci!\n");
			writer.write(formatter.format(date));
			System.out.println("\n");
			time();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	void PreviousTrans() {
		if (prevTrans > 0) {
			System.out.println("Vous avez déposé récemment: " + prevTrans + "bif");
		} else if (prevTrans < 0) {
			System.out.println("Vous avez retiré récemment: " + Math.abs(prevTrans) + "bif");
		} else if (r == false) {
			System.out.println("Vous avez pris récemment un credit de " + cr + " bif");
		} else if (r == true) {
			System.out.println("Vous avez remboursé récemment votre crédit");
		} else if(flag==0){
			 System.out.println("Vous avez récemment acheté des unités téléphoniques");
		}else{
			System.out.println("Il n'y a pas eu de transaction récemment...");
		}v
	}


	void credit() {
		Scanner dn = new Scanner(System.in);
		System.out.print("Quel montant voulez vous emprunter? ");
		cr = dn.nextInt();
		if (cr % 10 != 0) {
			System.err.print("Saisissez un montant multiple de 10");
		} else {
			if (cr > amount * 2) {
				System.err.print("Vous avez saisi une grosse somme! Max:" + amount * 2 + " bif");
			} else {
				credit = cr + cr / 10;
				amount += cr;
				System.out.print("Vous avez bel et bien pris un crédit de " + cr + "bif avec interet de " + cr / 10 + " bif\nVotre nouveau balance est de " + amount + " bif");
				boolean r = false;
			}
		}
	}


	void rembCredit() {
		Scanner gn = new Scanner(System.in);
		System.out.println("Voulez-vous rembourser votre crédit?(y/n)");
		char w = gn.next().charAt(0);
		if (w == 'y') {
			if (cr != 0 && cr < amount) {
				System.out.println("Vous aviez pris un crédit de " + (cr + cr / 10) + " bif");
				amount = amount - (cr + (cr / 10));
				System.out.println("Remboursé avec succès!");
				cr = 0;
				boolean r = true;
			} else if (cr > amount) {
				System.out.println("Votre balance est insuffisante...");
			}
		} else if (w == 'n') {
			System.out.println("A plus! n'oubliez pas de rembourser à la prochaine...");
		} else {
			System.err.println("Entrez une valeur correcte!");
		}
		boolean r = true;
	}


    void insertcontact() {
	   		int i=0,ctr=0;
	   		account a2=new account();
	   		try{
    	   		do{
        	   		FileWriter print = new FileWriter("/storage/emulated/0/Download/Facture/Contacto.txt");
        	   		String newLine = System.getProperty("line.separator");
        	   		System.out.println("Ajout Contact");
        	   		Scanner qt = new Scanner(System.in);
        	   		System.out.println("Entrez les contacts sous-forme: nom, ID");
        	   		a2.name= qt.next();
        	   		a2.account_no= qt.nextInt();
        	   		print.write(a2.name+" "+a2.account_no+newLine);
        	   		System.out.println("Voulez-vous ajouter un autre contact?");
        	   		int choix= qt.nextInt();
        	   		print.close();
    	   		    i++;
    	   		    ctr++;
    	   		}while(choix==1);
	   		} catch (IOException e) {
				e.printStackTrace();
			}
	}
            

	void service() {
		System.out.println("Bienvenue dans nos services!\nVoici nos services:\n1.Acheter un Bouquet Internet\n2.Payer un fournisseur.\n3.Achat des unités.\n4.Voir votre crédit.\n0.Back to menu");
		int z = qw.nextInt();
		System.out.print("\033[0;0H\033[2J");
		switch (z) {
		case 1:
			mega();
			break;
		case 2:
			fourn();
			break;
		case 3:
			tunes();
			break;
		case 4:
			checktunes();
			break;
		case 0:
			menu();
		default: {
			System.out.println("Entrez une valeur correcte");
		}
		}
	}

	void fourn() {
		int ctr = 0;
		account a3 = new account("Nathan", 101);
		String g = a3.name;
		Scanner rt = new Scanner(System.in);
		System.out.println("Bienvenue dans le service de Transfert!");
		System.out.println("A quel contact voulez vous transferer de l'argent?");
		fourn = rt.nextLine();
		if (fourn.equals(g)) {
			do {
				System.out.println("Entrez son identifiant");
				code = rt.nextInt();
				int o = a3.account_no;
				if (code == o) {
					System.out.println("Envoyer quel montant?");
					money = qw.nextInt();
					if (money < amount && amount > 0) {
						amount -= money;
						System.out.println("Succès!\nmontant envoyé");
					} else {
						System.out.println("Votre balance est insuffisant");
					}
				} else {
					ctr++;
					int essais=3-ctr;
					if(essais>0){
					System.out.println("Code erronné, Reste "+ essais +" essais");
					}
					else{
					    System.out.println("Code erronné.\nEssayer plus tard");
					}
				}
			} while (code != a3.account_no && ctr < 3);
		} else {
			System.err.println("le contact n'existe pas");

		}
	}


	void tunes() {
		System.out.println("Bienvenue dans le service d'achat d'unités");
		Scanner tr = new Scanner(System.in);
		System.out.println("Quel montant voulez-vous?");
		tunes = tr.nextInt();
		if (tunes < amount && tunes != 0 && amount > 0) {
			amount -= tunes;
			System.out.println("Achat avec succès de " + tunes + "f");
			flag=0;
		} else {
			System.out.println("Error!");
		}
	}



	void mega() {
		System.out.println("1. Bouquet social\n2. Bouquet illimité\n0.Retour au menu");
		int d = qw.nextInt();
		System.out.print("\033[0;0H\033[2J");
		switch (d) {
		case 1:
			social();
			break;
		case 2:
			illimite();
			break;
		case 0:
		    service();
		default: {
			System.out.println("Entrez une valeur correcte");
		}
		}
	}

	void social() {
		System.out.println("1. 2000f pour 1 semaine\n2. 3500f pour 1 mois\n0.Retour au menu");
		int a = qw.nextInt();
		System.out.print("\033[0;0H\033[2J");
		switch (a) {
		case 1:
			if (tunes >= 2000) {
				System.out.println("Bouquet social pour 1 semaine activé");
				tunes -= 2000;
			} else {
				System.err.println("Credit insuffisant");
			}
			break;
		case 2:
			if (tunes >= 3500) {
				System.out.println("Bouquet social pour 1 mois activé");
				tunes -= 3500;
			} else {
				System.err.println("Credit insuffisant");
			}
			break;
		case 0:
		    mega();
		    break;
		default: {
			System.out.println("Entrez une valeur correcte");
		}
		}
	}

	void illimite() {
		System.out.println("1. 5000f pour 700MB\n2. 10000 pour 1GB");
		int s = qw.nextInt();
		switch (s) {
		case 1:
			if (tunes >= 5000) {
				System.out.println("Bouquet illimité activé");
				tunes -= 5000;
			} else {
				System.err.println("Credit insuffisant");
			}
			break;
		case 2:
			if (tunes >= 10000) {
				System.out.println("Bouquet illimité activé");
				tunes -= 10000;
			} else {
				System.err.println("Credit insuffisant");
			}
			break;
		default: {
			System.err.println("Entrez une valeur correcte");
		}
		}
	}



	void Transf() {
		account a3 = new account("Nathan", 101);
		Scanner rt = new Scanner(System.in);
		System.out.println("Bienvenue dans le service de Transfert!");
		System.out.println("A quel contact voulez vous transferer de l'argent?");
		acc = rt.nextLine();
		if (acc.equals(a3.name)) {
			System.out.println("Envoyer quel montant?");
			tr = rt.nextInt();
			if (tr < amount && amount > 0) {
				amount -= tr;
				System.out.println("Succès!\nmontant envoyé");
			} else {
				System.out.println("Votre balance est insuffisant");
			}
		} else {
			System.err.println("le contact n'existe pas");
		}
	}
	void menu() {
		int op = 0;
		Scanner ch = new Scanner(System.in);
		do {
			System.out.print("\nVoici le menu\n\n0.Quitter et Sortir la facture\n1.Ajouter de l'argent\n2.Retirer de l'argent\n3.Afficher le solde\n4.Prendre un crédit\n5.Voir la transaction récente\n6.Rembourser le crédit.\n7.Insérer contacts.\n8.Transferer de l'argent à un ami.\n9.Autre Services.\nEntrez l'operation que vous voulez:");
			op = ch.nextInt();
			System.out.print("\033[0;0H\033[2J");

			switch (op) {
			case 1:
				deposit();
				checkbalance();
				System.out.println("\n***************\n");
				break;
			case 2:
				withdrawn();
				checkbalance();
				System.out.println("\n***************\n");
				break;
			case 3:
				display();
				System.out.println("\n***************\n");
				break;
			case 4:
				credit();
				quitter();
				System.out.println("\n***************\n");
				break;
			case 5:
				PreviousTrans();
				System.out.println("\n***************\n");
				break;
			case 6:
				rembCredit();
				System.out.println("\n***************\n");
				break;
			case 7:
				insertcontact();
				System.out.println("\n***************\n");
				break;
			case 8:
				Transf();
				System.out.println("\n***************\n");
				break;
			case 9:
				service();
				System.out.println("\n***************\n");
				break;
			case 0:
				System.out.println("La facture a  été créée avec succès!");
				quitter();
				break;
			default:
				System.err.println("Entrez une valeur correcte...");
			}
		} while (op != 0);
	}

}


class Testaccount {
	public static void main(String args []) {
		account a1 = new account();
		a1.insert();
		System.out.print("\033[0;0H\033[2J");
		a1.display();
		System.out.println("---------------------------------");
		a1.checkbalance();
		System.out.println("\n***************\n");
		a1.menu();
	}
}