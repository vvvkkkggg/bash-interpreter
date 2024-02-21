# Интерпретатор Bash

### Дизайн

#### Лексер

- Разбиваем на отдельные команды:
  - делаем split по переводящиму символу (`\n`);
  - делаем split по отделяющему символу (`;`);
  - собираем единый массив;
- Каждую строку разбиваем на токены по пробельным символам (`isspace`).
- Получаем массив массивов

#### Парсер


#### Пример

```bash
VAR1="VALUE"
VAR2="VALUE OF VAR1=$VAR1 $(echo 'echo')"
cat "./$VAR1" | wc -l | pwd | echo "SOME"
```

```python
tree = [
    {
        "type": "variable",
        "name": "VAR1",
        "exectute": [
            {
                "type": "literal",
                "value": "VALUE",
            }
        ]
    },
    {
        "type": "variable",
        "name": "VAR2",
        "execute": [
            {
                "type": "interpolation",
                "execute": [
                    {
                        "type": "literal", 
                        "value": "VALUE OF VAR1="
                    },
                    {
                        "type": "variable",
                        "name": "VAR1",
                    },
                ]
            },
        ]
    },
    {
        "type": "pipe",
        "execute": [
            {
                "type": "command",
                "execute": [
                    {
                        "type": "literal",
                        "value": "cat",
                    },
                    {
                        "type": "interpolation",
                        "execute": [
                            {
                                "type": "literal",
                                "execute": "./",
                            },
                            {
                                "type": "variable",
                                "name": "VAR1",
                            }
                        ]
                    }
                ]
            },
            {
                "type": "command",
                "execute": [
                    {
                        "type": "literal",
                        "value": "wc",
                    },
                    {
                        "type": "literal",
                        "value": "-l",
                    },
                ]
            },
            {
                "type": "command",
                "execute": [
                    {
                        "type": "literal",
                        "value": "pwd",
                    },
                ]
            },
            {
                "type": "command",
                "execute": [
                    {
                        "type": "literal",
                        "value": "echo",
                    },
                    {
                        "type": "literal",
                        "value": "SOME",
                    },
                ]
            },
        ]
    }
]

```

### Состав

```text
Гостило Роман
Смородский Артем
Куваев Максим
Косенко Алексей

Проект по System Design, 2024
```
