
base_de_donnees = {
    "voiture": {"disponible": True, "prix_jour": 150.000},
    "maison": {"disponible": True, "prix_jour":600.000},
    "assiette": {"disponible": True, "prix_jour": 100.000}
}


def afficher_disponibilites():
    """Affiche les objets disponibles à la location"""
    print("Objets disponibles à la location :")
    for objet, infos in base_de_donnees.items():
        statut = "Disponible" if infos["disponible"] else "Indisponible"
        print(f"- {objet} : {statut} (Prix/jour : {infos['prix_jour']}€)")

def louer_objet(objet, jours):
    """Permet de louer un objet pour un nombre de jours donné"""
    if objet not in base_de_donnees:
        print("Erreur : Objet non reconnu.")
        return
    
    if not base_de_donnees[objet]["disponible"]:
        print(f"Erreur : {objet} n'est pas disponible.")
        return
    
    try:
        jours = int(jours)
        if jours <= 0:
            print("Erreur : Le nombre de jours doit être positif.")
            return
    except ValueError:
        print("Erreur : Nombre de jours invalide.")
        return
    
    cout = base_de_donnees[objet]["prix_jour"] * jours
    base_de_donnees[objet]["disponible"] = False
    
    print(f"Vous avez loué {objet} pour {jours} jours. Coût total : {cout}€")

def retourner_objet(objet):
    """Permet de retourner un objet loué"""
    if objet not in base_de_donnees:
        print("Erreur : Objet non reconnu.")
        return
    
    if base_de_donnees[objet]["disponible"]:
        print(f"Erreur : {objet} n'est pas actuellement loué.")
        return
    
    base_de_donnees[objet]["disponible"] = True
    print(f"{objet} a été retourné avec succès.")


def menu_principal():
    """Affiche un menu interactif pour gérer les locations"""
    while True:
        print("\n--- Menu Principal ---")
        print("1. Afficher les disponibilités")
        print("2. Louer un objet")
        print("3. Retourner un objet")
        print("4. Quitter")
        
        choix = input("Votre choix (1-4) : ")
        
        if choix == "1":
            afficher_disponibilites()
        elif choix == "2":
            objet = input("Quel objet voulez-vous louer ? ").lower()
            jours = input("Pour combien de jours ? ")
            louer_objet(objet, jours)
        elif choix == "3":
            objet = input("Quel objet voulez-vous retourner ? ").lower()
            retourner_objet(objet)
        elif choix == "4":
            print("Merci d'avoir utilisé notre service de location. Au revoir !")
            break
        else:
            print("Choix invalide. Veuillez sélectionner une option entre 1 et 4.")


if __name__ == "__main__":
    print("Bienvenue dans location !")
    menu_principal()

