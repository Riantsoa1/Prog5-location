data_base <- list(
    voiture = list(disponible = TRUE , prix_jour = 150.000),
    maison = list(disponible = TRUE , prix_jour = 600.000),
    assiette = list(disponible = TRUE , prix_jour = 100.000)
)

est_disponible <- function() {
    cat("Objet libre en location :\n")
    for(objet in names(data_base)){
         statut <- if (data_base[[objet]]$disponible) "Disponible" else "Indisponible"
    cat(sprintf("- %s : %s (Prix/jour : %d€)\n", 
                objet, statut, data_base[[objet]]$prix_jour))
    }
}

louer <- function(objet, jours){
    if (!objet %in% names(data_base)) {
    cat("Erreur : Objet non reconnu.\n")
    return()
  }
  if (!data_base[[objet]]$disponible) {
    cat("Erreur :", objet, "n'est pas disponible.\n")
    return()
  }
  if (!is.numeric(jours) || jours <= 0) {
    cat("Erreur : Nombre de jours invalide.\n")
    return()
  }
  
  cout <- data_base[[objet]]$prix_jour * jours
  data_base[[objet]]$disponible <<- FALSE
  
  cat(sprintf("Vous avez loué %s pour %d jours. Coût total : %d€\n", 
              objet, jours, cout))
}


retourner <- function(objet) {
  if (!objet %in% names(data_base)) {
    cat("Erreur : Objet non reconnu.\n")
    return()
  }
  if (data_base[[objet]]$disponible) {
    cat("Erreur :", objet, "n'est pas actuellement loué.\n")
    return()
  }
  
  data_base[[objet]]$disponible <<- TRUE
  cat(objet, "a été retourné avec succès.\n")
}

est_disponible()
louer("voiture", 3)
est_disponible()
retourner("voiture")
est_disponible()