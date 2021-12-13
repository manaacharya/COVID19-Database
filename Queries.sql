/** 
	Count of patients with mild symptoms

	Returns the total count of patients who have mild symptoms of COVID-19. 

	created: Oct. 23
	author: team 7
**/
SELECT COVID_SEVERITY, count(*) 
FROM PATIENTS 
WHERE COVID_SEVERITY = "MILD";

/** 
	Health Issues correlating to COVID-19 Severity 

	Returns patient ID of patients who have more 2 or more pre-exisiting 
    health conditions and the severity of their COVID-19 symptoms. 

	created: Oct. 23
	author: team 7
**/
SELECT PATIENT_ID, COVID_SEVERITY, HEALTH_ISSUES
FROM PATIENTS
WHERE HEALTH_ISSUES >= 2;

/** 
	States with more than 20,000 deaths 

	Returns the name of states that have more than 20,000 deaths.

	created: Oct. 23
	author: team 7
**/
SELECT STATE_NAME, DEATH_No 
FROM STATES 
WHERE DEATH_No > 20000;

/** 
	States with more than 100,000 cases 

	Returns the name of states that have more than 100,000 cases of COVID-19. 

	created: Oct. 23
	author: team 7
**/
SELECT STATE_NAME, CASE_NO 
FROM STATES 
WHERE CASE_NO > 100000;

/** 
	States with the greatest deaths  

	Returns the name of states in descending order in the number of deaths from COVID-19. 

	created: Oct. 23
	author: team 7
**/
SELECT STATE_NAME, DEATH_NO 
FROM STATES 
ORDER BY DEATH_NO DESC;

/** 
	Patients with severe cases

	Returns the patient ID of patients who have severe cases of COVID 

	created: Oct. 23
	author: team 7
**/

SELECT PATIENT_ID, COVID_SEVERITY 
FROM PATIENTS 
WHERE COVID_SEVERITY = “SEVERE”;

/** 
	States with the least amount of cases 

	Returns a list of states that have the lowest number of cases of COVID-19. 

	created: Oct. 23
	author: team 7
**/
SELECT STATES.STATE_NAME, CASES.CASE_NO 
FROM STATES, CASES 
WHERE STATES.CASE_NO = CASES.CASE_NO 
ORDER BY CASES.CASE_NO;

/** 
	Patient severity count based on race 

	Returns a count of the severe cases organized by the patients race/ethnicity. 

	created: Oct. 23
	author: team 7
**/
SELECT PATIENT_RACE, COUNT(*) 
FROM PATIENTS 
WHERE COVID_SEVERITY = "SEVERE" 
GROUP BY PATIENT_RACE 
ORDER BY COUNT(*);

/** 
	COVID Severity correlating with death  

	Returns the death number per type of COVID symptom severity. 

	created: Oct. 23
	author: team 7
**/
SELECT COVID_SEVERITY, DEATH_NO 
FROM COVID_SEVERITY 
ORDER BY DEATH_NO DESC;

/** 
	COVID patients from what state

	Returns the state name and a count of COVID-19 patients that are from that state. 

	created: Oct. 23
	author: team 7
**/
SELECT STATE_NAME, COUNT(*) 
FROM PATIENTS 
GROUP BY STATE_NAME 
ORDER BY COUNT(*) DESC;

/** 
	Patient gender and severity count 

	Returns the count of severe COVID symptom cases per each patient sex. 
    Sex with the highest number of COVID severe cases is list first. 

	created: Oct. 23
	author: team 7
**/
SELECT PATIENT_SEX, COVID_SEVERITY, COUNT(*)
FROM PATIENTS
WHERE COVID_SEVERITY = "SEVERE"
GROUP BY PATIENT_SEX
ORDER BY COUNT(*) DESC;