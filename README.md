# TD7

## Mise en contexte
L'application incluse dans ce TD utilise une persistence avec une base de donnée H2 et l'ORM Hibernate. (Pour un rappel sur ces technologies, revoir le TD5)

C'est une application permettant de réserver une croisière. Une seule croisière est enregistrée par défaut.
* Identifiant: "ALASKA"
* Date et heure de départ: 2085-01-25, 12:00
* Date et heure de fin: 2085-02-01, 12:00

Elle contient les fonctionnalités suivantes:

#### 1. Réserver une croisière

**Conditions:**
* La réservation d'une croisière est possible selon la disponibilité des cabines.
* La réservation d'une croisière est possible si la date de réservation est avant la date de départ de la croisière.
* La réservation d'une croisière est possible avec au moins un voyageur.

POST cruises/{cruiseId}/bookings (cruises/ALASKA/bookings)

```
{
    "travelers": [
        {
            "name": "John Doe"::string,
            "category": "child"::string(CHILD | ADULT | SENIOR)
        },
        {
            "name": "Jane Doe"::string,
            "category": "adult"::string(CHILD | ADULT | SENIOR)
        }
    ],
    "cabinType": "Standard"::string(STANDARD | DELUXE | SUITE),
    "bookingDateTime": "2084-04-08T12:30"::string(dateTime, format ISO)
}
```

Réponses:

HTTP 201 CREATED

Headers: `Location: cruises/<cruiseId>/bookings/<bookingId>`

Si un des champs est manquant:

HTTP 400 BAD REQUEST

```
{
    "error": "MISSING_PARAMETER",
    "message": "Missing parameter: <parameterName>"
}
```

Si la bookingDate n'est pas dans le bon format:

HTTP 400 BAD REQUEST

```
{
"error": "INVALID_DATE_FORMAT",
"message": "Invalid date format"
}
```

Si la croisière n'existe pas:

HTTP 404 NOT FOUND

```
{
    "error": "CRUISE_NOT_FOUND",
    "message": "Cruise not found"
}
```

Si la date de réservation n'est pas avant le départ de la croisière:

HTTP 400 BAD REQUEST

```
{
    "error": "INVALID_PARAMETER",
    "description": "Invalid booking date."
}
```

Si le type de cabine n'est pas valide (STANDARD, DELUXE, SUITE):

HTTP 400 BAD REQUEST

```
{
    "error": "INVALID_PARAMETER",
    "description": "Invalid cabin type."
}
```

Si la catégorie d'un voyageur n'est pas valide (CHILD, ADULT, SENIOR):

HTTP 400 BAD REQUEST

```
{
    "error": "INVALID_PARAMETER",
    "description": "Invalid traveler category."
}
```

Si la réservation ne contient aucun voyageur:

HTTP 400 BAD REQUEST

```
{
    "error": "NO_TRAVELER",
    "description": "No traveler to book."
}
```

Si le type de cabine n'est plus disponible:

HTTP 400 BAD REQUEST

```
{
    "error": "CABIN_TYPE_NOT_AVAILABLE",
    "description": "Cabin type not available"
}
```

#### 2. Consulter une réservation

GET cruises/{cruiseId}/bookings/{bookingId} (cruises/ALASKA/bookings/1)

Réponses:

HTTP 200 OK

```
{
    "cruiseId": "ALASKA"::string,
    "bookingId": "a6d1a050-e4e2-451a-a315-45e2d551ed33"::string,
    "travelers": [
        {
            "id": ""395e7715-74fc-468a-9795-1adc79e6148a"::string,
            "name": "John Doe"::string,
            "category": "CHILD"::string(CHILD | ADULT | SENIOR)
        },
        {
            "id": "093f9a8d-f7c6-4d3d-b878-90452922e768"::string,
            "name": "Jane Doe"::string,
            "category": "ADULT"::string(CHILD | ADULT | SENIOR)
        }
    ],
    "cabinType": "STANDARD"::string(STANDARD | DELUXE | SUITE),
    "bookingDateTime": "2084-04-08T12:30"::string(dateTime, format ISO)
}
```

Si la croisière n'existe pas:

HTTP 404 NOT FOUND

```
{
    "error": "CRUISE_NOT_FOUND",
    "message": "Cruise not found"
}
```

Si la réservation n'existe pas:

HTTP 404 NOT FOUND

```
{
    "error": "BOOKING_NOT_FOUND",
    "message": "Booking not found"
}
```

## Exercice: Élaborer la stratégie d'essais pour la fonctionnalité de réservation d'une croisière.

1. Suivez le gabarit ci-dessous pour écrire le scénario d'essais **pour la réservation d'une croisière**.
2. Implémenter les tests.
   - Vous pouvez utiliser les valeurs par défaut de `Main`.

**TESTS FONCTIONS:**

- Listez vos tests unitaires ici
- Nom du test: Pourquoi c'est un test unitaire

Exemple:
- _Valider qu'aucun champ n'est manquant: tester que chaque champ est présent avec seulement la fonction de validation "XYZ"_



**TESTS COMPOSANT:**

- Listez vos tests composant ici
- Nom du test: Pourquoi c'est un test composant



**TESTS MULTI-COMPOSANTS (Cucumber):**

- Listez vos tests multi-composants ici
- Nom du test: Pourquoi c'est un test multi-composant

### Comment élaborer une bonne stratégie d'essais
- Respecter la pyramide des essais
- Respecter le périmètre des différents types de tests
- Boulonner les essais
- Un test ne devrait avoir qu'une seule raison d'échouer!

### Rappels Cucumber
#### Critères Cucumber 
* Les tests Cucumber doivent être écrits en français (vous devez utiliser les mots clés en français: Fonctionnalité, Etant donné, Quand, etc).
    * Les ENUM, les noms de champs et les noms d'erreurs peuvent être en anglais.
* Un scénario = Un test
* Les titres des scénarios doivent être clairs et auto-descriptifs.
* Le contenu des scénarios doit être facilement lisible.
* Le contenu des scénarios doit suivre la formule Given, When, Then (ou Étant donné, Quand, Alors).
* Les définitions d'étapes (Step Definitions) ne doivent pas avoir de duplications de code excessives et doivent être réutilisables.

#### Comment mettre Cucumber en place
1. Créer le `RunnerTest` pointant vers le dossier des StepDefinitions (`glue`)
  ``` java
   @RunWith(Cucumber.class)
   @CucumberOptions(glue = {"td7.multiComponents.glue"})
   public class TestRunner {}
  ```
2. Implémenter vos scénarios dans un fichier _.feature_ dans `test/resources/td7/multiComponents/features` ([Comment écrire un scénario](https://cucumber.io/docs/guides/10-minute-tutorial/#write-a-scenario))
3. Implémenter les StepDefinition dans le dossier spécifié au TestRunner (`glue`). ([Comment implémenter un StepDefinition](https://cucumber.io/docs/cucumber/step-definitions))
   - Le plus simple est d'utiliser [REST Assured](https://rest-assured.io/) pour appeler l'API et valider les réponses.
4. Ajouter `@Before` et `@After` dans le StepDefinition pour démarrer et fermer le serveur pour les tests.
   - Pour utiliser la base de données en mémoire, utilisez la méthode `ConfigurationServerRest.useInMemoryRepositories()` avant de démarrer le serveur.
5. Lancer vos tests Cucumber à partir du `RunnerTest` et en exécutant `mvn test`.
