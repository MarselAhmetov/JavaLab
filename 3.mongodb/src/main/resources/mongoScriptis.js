use project

db.employees.find(
    {
        firstName: 'Marsel'
    })

db.companies.insertOne(
    {
        name: 'Company0',
        description: 'First company'
    }
)

db.companies.insertMany([
        {
            name: 'Company1',
            description: 'Cool company'
        },
        {
            name: 'Company2',
            sphere: 'IT'
        },
        {
            name: 'Company2.1',
            sphere: 'IT'
        },
        {
            name: 'Company2.2',
            sphere: 'IT'
        },
        {
            name: 'Company3',
            description: 'Very cool company',
            sphere: 'Very IT'
        }

    ]
);

db.employees.insertMany([
    {
        firstName: 'Marsel',
        lastName: 'Marselevich',

    },
    {
        name: 'Esche Marsel',
        age: 20,
    },
    {
        name: 'One Marsel more',
        lastName: 'Marselevidze',
        age: 'Десять'
    }

]);

db.projects.update({_id: ObjectId('5fd0e87f81cd274ee1348193')},
    {
        $set: {
            employee: ObjectId('5fd0e8f2424ad9355d52c18e')
        },
        $push: {
            task: ObjectId('5fe7f4b4f729a619d8bb38d2')
        }
    })

db.companies.deleteOne(
    {
        name: "Company2"
    }
)

db.companies.deleteMany(
    {
        sphere: "IT"
    }
)
