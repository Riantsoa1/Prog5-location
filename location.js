
const baseDeDonnees = {
    voiture: { disponible: true, prixJour: 150.000 },
    maison: { disponible: true, prixJour: 600.000 },
    assiette: { disponible: true, prixJour: 100.000 }
  };
  

  function afficherDisponibilites() {
    console.log("Objets disponibles à la location :");
    for (const [objet, infos] of Object.entries(baseDeDonnees)) {
      const statut = infos.disponible ? "Disponible" : "Indisponible";
      console.log(`- ${objet} : ${statut} (Prix/jour : ${infos.prixJour}€)`);
    }
  }


  function louerObjet(objet, jours) {
    if (!baseDeDonnees[objet]) {
      console.log("Erreur : Objet non reconnu.");
      return;
    }
    if (!baseDeDonnees[objet].disponible) {
      console.log(`Erreur : ${objet} n'est pas disponible.`);
      return;
    }
    if (isNaN(jours) || jours <= 0) {
      console.log("Erreur : Nombre de jours invalide.");
      return;
    }
  
    const cout = baseDeDonnees[objet].prixJour * jours;
    baseDeDonnees[objet].disponible = false;
  
    console.log(`Vous avez loué ${objet} pour ${jours} jours. Coût total : ${cout}€`);
  }
  

  function retournerObjet(objet) {
    if (!baseDeDonnees[objet]) {
      console.log("Erreur : Objet non reconnu.");
      return;
    }
    if (baseDeDonnees[objet].disponible) {
      console.log(`Erreur : ${objet} n'est pas actuellement loué.`);
      return;
    }
  
    baseDeDonnees[objet].disponible = true;
    console.log(`${objet} a été retourné avec succès.`);
  }
  

  afficherDisponibilites();
  louerObjet("voiture", 3);
  afficherDisponibilites();
  retournerObjet("voiture");
  afficherDisponibilites();