\# Douane Temporelle



\## Présentation du projet



Douane Temporelle est une application Android qui simule un système de contrôle douanier pour les voyageurs dans le temps.



L'application permet à un voyageur de saisir ses informations personnelles, de choisir une année de destination et de préciser le motif de son voyage.



\## Fonctionnalités



\- Saisie du nom du voyageur.

\- Saisie de l'année de naissance.

\- Choix de l'année de destination.

\- Sélection du motif du voyage : Tourisme, Modification du passé ou Fuite.

\- Affichage d'un passeport temporel.

\- Vérification du risque de paradoxe temporel.

\- Approbation ou refus du voyage.

\- Affichage du résultat final.

\- Possibilité de commencer un nouveau voyage.



\## Technologies utilisées



\- Android Studio

\- Java

\- XML

\- Git et GitHub



\## Structure de l'application



L'application contient trois activités principales :



1\. \*\*MainActivity :\*\* formulaire de saisie des informations.

2\. \*\*VerificationActivity :\*\* affichage du passeport et vérification du voyage.

3\. \*\*ResultActivity :\*\* affichage du résultat de la décision.



\## Règles de validation



\- Tous les champs doivent être remplis.

\- Les années doivent être positives.

\- L'année de naissance ne doit pas être dans le futur.

\- L'année de destination doit être supérieure à l'année de naissance.

\- Les années doivent être saisies sous forme numérique.



\## Règle de sécurité temporelle



Si le motif est « Modification du passé » et que la différence entre l'année de destination et l'année de naissance est un nombre premier, le bouton Approuver est désactivé.



\## Tests réalisés



\- Vérification des champs vides.

\- Vérification des années invalides.

\- Vérification d'une année de naissance dans le futur.

\- Test de l'approbation du voyage.

\- Test du refus du voyage.

\- Test de la règle des nombres premiers.

\- Test du bouton Nouveau voyageur.



\## Objectif



L'objectif est de développer une application Android simple permettant de simuler le contrôle des voyages temporels et la détection des risques de paradoxe.

