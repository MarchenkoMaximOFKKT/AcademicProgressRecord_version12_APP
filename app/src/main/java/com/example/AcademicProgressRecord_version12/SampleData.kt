package com.example.AcademicProgressRecord_version12
val sampleSubjects = listOf(
    Subject(1, "Мобільна розробка", targetScore = 90, currentScore = 41.6, attendancePercent = 92, status = SubjectStatus.AT_RISK),
    Subject(2, "Бази даних", targetScore = 80, currentScore = 49.8, attendancePercent = 85, status = SubjectStatus.SAFE),
    Subject(3, "Алгоритми", targetScore = 75, currentScore = 35.0, attendancePercent = 60, status = SubjectStatus.CRITICAL),
    Subject(4, "Операційні системи", targetScore = 85, currentScore = 88.0, attendancePercent = 100, status = SubjectStatus.SAFE),
    Subject(5, "Мережі", targetScore = 80, currentScore = 64.0, attendancePercent = 78, status = SubjectStatus.AT_RISK),
    Subject(6, "ОПІ", targetScore = 90, currentScore = 91.0, attendancePercent = 95, status = SubjectStatus.SAFE),
    Subject(7, "Нейронні мережі", targetScore = 70, currentScore = 55.0, attendancePercent = 88, status = SubjectStatus.SAFE),
    Subject(8, "Python", targetScore = 75, currentScore = 30.0, attendancePercent = 70, status = SubjectStatus.CRITICAL)
)
val sampleComponents = listOf(
    GradeComponent(1, 1, "Лабораторні", 30.0, 100.0, 88.0),
    GradeComponent(2, 1, "Модуль 1", 20.0, 100.0, 76.0),
    GradeComponent(3, 1, "Екзамен", 50.0, 100.0),
    GradeComponent(4, 2, "Лабораторні", 40.0, 100.0, 92.0),
    GradeComponent(5, 2, "Модуль 1", 20.0, 100.0, 65.0),
    GradeComponent(6, 2, "Екзамен", 40.0, 100.0),
    GradeComponent(7, 3, "Практичні", 50.0, 100.0, 70.0),
    GradeComponent(8, 3, "Екзамен", 50.0, 100.0)
)