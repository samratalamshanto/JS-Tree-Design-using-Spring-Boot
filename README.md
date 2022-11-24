# JS-Tree-Design-using-Spring-Boot

<img width="374" alt="image" src="https://user-images.githubusercontent.com/38380356/203811851-abe4ce52-0166-4860-94d6-8c7dac7f75d9.png">
<img width="693" alt="image" src="https://user-images.githubusercontent.com/38380356/203811941-55cd167c-a277-4e57-9ce9-1bb1a34ee62f.png">
<img width="373" alt="image" src="https://user-images.githubusercontent.com/38380356/203812000-cb65fcf8-f7c0-4e1b-ae7a-0b32844f07e5.png">



# Demo Data:
```json
[
    {
        "entityName": "ceo1",
        "parentId": 0,
        "id": 1
    },
    {
        "entityName": "ceo2",
        "parentId": 0,
        "id": 2
    },
    {
        "entityName": "vp1",
        "parentId": 1,
        "id": 3
    },
    {
        "entityName": "vp2",
        "parentId": 1,
        "id": 4
    },
    {
        "entityName": "vp3",
        "parentId": 1,
        "id": 5
    },
    {
        "entityName": "vp4",
        "parentId": 2,
        "id": 6
    },
    {
        "entityName": "ceo3",
        "parentId": 0,
        "id": 7
    },
    {
        "entityName": "ceo4",
        "parentId": 0,
        "id": 8
    },
    {
        "entityName": "lead1-4",
        "parentId": 6,
        "id": 9
    }
]
```

# Demo Result:
```json
{
    "ceo2": {
        "parentName": "",
        "isParent": 1,
        "hasChild": 1,
        "child": [
            {
                "vp4": {
                    "parentName": "ceo2",
                    "isParent": 0,
                    "hasChild": 1,
                    "child": [
                        {
                            "lead1-4": {
                                "parentName": "vp4",
                                "isParent": 0,
                                "hasChild": 1,
                                "child": [
                                    {
                                        "soft-developer": {
                                            "parentName": "lead1-4",
                                            "isParent": 0,
                                            "hasChild": 0,
                                            "child": null
                                        }
                                    }
                                ]
                            }
                        }
                    ]
                }
            }
        ]
    }
}
```
