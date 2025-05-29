# 🧼 Clean Architecture en Java – Exemple de Compteur

Ce projet est une **démonstration simple** de la **Clean Architecture** en Java pur, sans framework. Il implémente un **compteur avec bouton**, dont la valeur est **persistée dans un fichier local**.

---

## 🎯 Objectif

- Un bouton qui **incrémente** un compteur.
- Le compteur est **chargé et sauvegardé** dans un fichier local (`counter.txt`).
- L'application suit les principes de **Clean Architecture** pour une structure claire, testable et maintenable.

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
- `state/` → Représente l’état affiché à l’écran

> 💡 Elle interagit uniquement avec les **cas d’usage** (`usecase`) via les **contrôleurs**.

---

### 🔹 `/domain`

Couche **centrale** : contient la **logique métier pure**.

- `model/` → Les entités métiers (ex: `Counter`)
- `usecase/` → Les cas d’usage (ex: `IncrementCounterUseCase`)
- `repository/` → Interfaces des dépôts de données (ex: `CounterRepository`)

> 💡 Cette couche **ne dépend de rien**. Elle est **totalement testable** et réutilisable.

---

### 🔸 `/data`

Couche **d'infrastructure** : elle implémente les interfaces de la couche `domain`.

- `datasource/` → Accès aux données (ex: lecture/écriture fichier)
- `repository/` → Implémentations concrètes des interfaces `domain.repository`

> 💡 Cette couche **dépend du domaine**, mais jamais l'inverse.

---


## ⚙️ Fonctionnement

1. **Lancement** → le compteur est chargé depuis `counter.txt`.
2. **Clique sur le bouton** → le compteur est incrémenté.
3. **Mise à jour de la vue** et **sauvegarde** de la nouvelle valeur dans le fichier.

---

## ✅ Avantages de cette Architecture

- **Séparation des responsabilités** nette.
- **Testabilité accrue** (logique métier testable indépendamment de l’UI).
- **Évolutivité** : facile à remplacer une couche (ex: changer la persistance fichier → base de données).
- **Lisibilité et maintenabilité** du code.

---

## 📁 Exemple de hiérarchie

```
/src
├── domain
│ ├── model
│ ├── repository
│ └── usecase
├── data
│ ├── datasource
│ └── repository
└── presentation
├── controller
├── state
└── view
```


---

## 📝 Auteur

Projet écrit par **Pellicane Alessio** pour illustrer les principes de la Clean Architecture avec un exemple simple, pédagogique et sans dépendances externes.

---
