# 🧼 Clean Architecture en Java – Application Todo List

Ce projet est une **démonstration** de la **Clean Architecture** en Java pur, sans framework. Il implémente une **application de gestion de tâches** (Todo List) dont les données sont **persistées dans un fichier local**.

---

## 🎯 Objectif

- Une interface permettant d'**ajouter** de nouvelles tâches
- La possibilité de **supprimer** des tâches
- Les tâches sont **chargées et sauvegardées** dans un fichier local
- L'application suit les principes de **Clean Architecture** pour une structure claire, testable et maintenable

---

## 📚 Qu'est-ce que la Clean Architecture ?

La Clean Architecture vise à organiser le code en **couches indépendantes**. Chaque couche a une **responsabilité bien définie** et dépend uniquement des couches **plus internes**, jamais des couches externes.

### 📁 Structure typique :
```
/presentation → Interface utilisateur et logique d'interaction
/domain → Règles métier pures (indépendantes du système)
/data → Accès aux données (fichiers, bases, API...)
```

---

## 🧩 Description des Couches

### 🟢 `/presentation`

Couche **UI / Application** : contient l’interface utilisateur et les contrôleurs.

- `view/` → Interface graphique (`Swing`)
- `controller/` → Gère les actions utilisateur
- `state/` → Représente l'état affiché à l'écran

> 💡 Elle interagit uniquement avec les **cas d'usage** (`usecase`) via les **contrôleurs**.

#### Flux de données :
1. L'utilisateur interagit avec la `view`
2. La `view` notifie le `controller`
3. Le `controller` appelle le `usecase` approprié
4. Le `controller` met à jour le `state`
5. La `view` se met à jour en fonction du `state`

---

### 🔹 `/domain`

Couche **centrale** : contient la **logique métier pure**.

- `entity/` → Les entités métiers (ex: `TodoItem`)
- `usecase/` → Les cas d'usage (ex: `AddTodoUseCase`, `DeleteTodoUseCase`)
- `repository/` → Interfaces des dépôts de données (ex: `TodoRepository`)

> 💡 Cette couche **ne dépend de rien**. Elle est **totalement testable** et réutilisable.

#### Flux de données :
1. Les `usecase` manipulent les `entity`
2. Les `usecase` utilisent les interfaces `repository` pour la persistance
3. Les règles métier sont appliquées dans les `usecase`

---

### 🔸 `/data`

Couche **d'infrastructure** : elle implémente les interfaces de la couche `domain`.

- `model/` → Modèles de données pour la persistance (ex: `TodoItemModel`)
- `datasource/` → Accès aux données (ex: lecture/écriture fichier)
- `repository/` → Implémentations concrètes des interfaces `domain.repository`

> 💡 Cette couche **dépend du domaine**, mais jamais l'inverse.

#### Flux de données :
1. Les `repository` utilisent les `datasource` pour accéder aux données
2. Les `model` représentent la structure des données persistées

---

## ⚙️ Fonctionnement

1. **Lancement** → les tâches sont chargées depuis le fichier de persistance
   - Le `datasource` lit le fichier
   - Les entités sont chargées dans l'application

2. **Ajout d'une tâche** → nouvelle tâche créée et sauvegardée
   - La `view` capture l'entrée utilisateur
   - Le `controller` appelle `AddTodoUseCase`
   - Le `usecase` crée une nouvelle entité
   - Le `repository` persiste l'entité via le `datasource`

3. **Suppression d'une tâche** → suppression et mise à jour du fichier
   - La `view` capture la sélection
   - Le `controller` appelle `DeleteTodoUseCase`
   - Le `usecase` demande la suppression au `repository`
   - Le `repository` met à jour le fichier via le `datasource`

---

## ✅ Avantages de cette Architecture

- **Séparation des responsabilités** nette
- **Testabilité accrue** (logique métier testable indépendamment de l'UI)
- **Évolutivité** : facile à remplacer une couche (ex: changer la persistance fichier → base de données)
- **Lisibilité et maintenabilité** du code

---

## 📁 Exemple de hiérarchie

```
/src
├───core
├───data
│   ├───model
│   ├───datasource
│   ├───repository
├───domain
│   ├───entity
│   ├───usecase
│   └───repository
└───presentation
    ├───view
    ├───controller
    └───state
```


---

## 📝 Auteur

Projet écrit par **Pellicane Alessio** pour illustrer les principes de la Clean Architecture avec un exemple simple, pédagogique et sans dépendances externes.

---
