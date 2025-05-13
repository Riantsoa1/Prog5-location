import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Location {
    
    static class ObjetLocation {
        boolean disponible;
        int prixJour;
        
        public ObjetLocation(boolean disponible, int prixJour) {
            this.disponible = disponible;
            this.prixJour = prixJour;
        }
    }
    
    private static Map<String, ObjetLocation> baseDeDonnees = new HashMap<>();
    
    static {
        baseDeDonnees.put("voiture", new ObjetLocation(true, 150.000));
        baseDeDonnees.put("maison", new ObjetLocation(true, 600.000));
        baseDeDonnees.put("assiette", new ObjetLocation(true, 100.000));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le système de location !");
        
        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Afficher les disponibilités");
            System.out.println("2. Louer un objet");
            System.out.println("3. Retourner un objet");
            System.out.println("4. Quitter");
            System.out.print("Votre choix (1-4) : ");
            
            String choix = scanner.nextLine();
            
            switch (choix) {
                case "1":
                    afficherDisponibilites();
                    break;
                case "2":
                    System.out.print("Quel objet voulez-vous louer ? ");
                    String objetALouer = scanner.nextLine().toLowerCase();
                    System.out.print("Pour combien de jours ? ");
                    String joursStr = scanner.nextLine();
                    louerObjet(objetALouer, joursStr);
                    break;
                case "3":
                    System.out.print("Quel objet voulez-vous retourner ? ");
                    String objetARetourner = scanner.nextLine().toLowerCase();
                    retournerObjet(objetARetourner);
                    break;
                case "4":
                    System.out.println("Merci d'avoir utilisé notre service de location. Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option entre 1 et 4.");
            }
        }
    }
    
    private static void afficherDisponibilites() {
        System.out.println("Objets disponibles à la location :");
        for (Map.Entry<String, ObjetLocation> entry : baseDeDonnees.entrySet()) {
            String statut = entry.getValue().disponible ? "Disponible" : "Indisponible";
            System.out.printf("- %s : %s (Prix/jour : %d€)%n", 
                            entry.getKey(), statut, entry.getValue().prixJour);
        }
    }
    
    private static void louerObjet(String objet, String joursStr) {
        if (!baseDeDonnees.containsKey(objet)) {
            System.out.println("Erreur : Objet non reconnu.");
            return;
        }
        
        ObjetLocation obj = baseDeDonnees.get(objet);
        if (!obj.disponible) {
            System.out.printf("Erreur : %s n'est pas disponible.%n", objet);
            return;
        }
        
        try {
            int jours = Integer.parseInt(joursStr);
            if (jours <= 0) {
                System.out.println("Erreur : Le nombre de jours doit être positif.");
                return;
            }
            
            int cout = obj.prixJour * jours;
            obj.disponible = false;
            System.out.printf("Vous avez loué %s pour %d jours. Coût total : %d€%n", 
                            objet, jours, cout);
        } catch (NumberFormatException e) {
            System.out.println("Erreur : Nombre de jours invalide.");
        }
    }
    
    private static void retournerObjet(String objet) {
        if (!baseDeDonnees.containsKey(objet)) {
            System.out.println("Erreur : Objet non reconnu.");
            return;
        }
        
        ObjetLocation obj = baseDeDonnees.get(objet);
        if (obj.disponible) {
            System.out.printf("Erreur : %s n'est pas actuellement loué.%n", objet);
            return;
        }
        
        obj.disponible = true;
        System.out.printf("%s a été retourné avec succès.%n", objet);
    }
}