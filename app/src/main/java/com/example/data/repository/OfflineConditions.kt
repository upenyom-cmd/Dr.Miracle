package com.example.data.repository

import com.example.data.model.MedicalCondition
import com.example.data.model.CategoryInfo

object OfflineConditions {

    val categories = listOf(
        CategoryInfo("Cardiovascular", "Conditions affecting the heart and blood vessels.", "ic_heart"),
        CategoryInfo("Respiratory", "Lungs and airway breathing problems.", "ic_lungs"),
        CategoryInfo("Gastrointestinal", "Digestive system and abdominal issues.", "ic_stomach"),
        CategoryInfo("Endocrine", "Hormonal and metabolic disorders.", "ic_hormone"),
        CategoryInfo("Dermatology", "Skin, rash, and allergy-related conditions.", "ic_skin"),
        CategoryInfo("Neurology", "Nervous system and headache issues.", "ic_brain"),
        CategoryInfo("Musculoskeletal", "Joint, muscle, and bone conditions.", "ic_bone"),
        CategoryInfo("Infectious Diseases", "Bacterial, viral, and localized infections.", "ic_virus"),
        CategoryInfo("EMERGENCY CARE", "Urgent life-threatening medical manifestations.", "ic_warning")
    )

    val conditions = listOf(
        MedicalCondition(
            id = "hypertension",
            name = "Hypertension (High Blood Pressure)",
            category = "Cardiovascular",
            shortDescription = "A common condition in which the long-term force of the blood against your artery walls is consistently too high.",
            fullDescription = "Hypertension is defined as having blood pressure readings consistently at or above 130/80 mmHg. Often called a 'silent killer' because it usually exhibits no symptoms, it is a primary risk factor for stroke, heart attack, heart failure, and chronic kidney disease if left unmanaged over many years.",
            symptoms = listOf(
                "Usually asymptomatic (the 'Silent Killer')",
                "Dull headaches (especially with severe readings > 180/120)",
                "Dizziness or lightheadedness",
                "Shortness of breath on exertion",
                "Nosebleeds (rare, occurs in severe crises)"
            ),
            firstLineTreatments = listOf(
                "Therapeutic Lifestyle Changes (TLC): Dietary Approaches to Stop Hypertension (DASH) diet high in vegetables, fruits, and low-fat dairy.",
                "Sodium restriction to less than 1,500 - 2,300 mg per day.",
                "Regular aerobic physical activity (at least 150 minutes of moderate-intensity exercise per week).",
                "Body weight optimization (achieving a healthy BMI between 18.5 and 24.9)."
            ),
            prescriptionTreatments = listOf(
                "Thiazide-type diuretics (e.g., Chlorthalidone, Hydrochlorothiazide)",
                "Angiotensin-Converting Enzyme (ACE) Inhibitors (e.g., Lisinopril, Enalapril)",
                "Angiotensin Receptor Blockers (ARBs) (e.g., Losartan, Valsartan)",
                "Calcium Channel Blockers (CCBs) (e.g., Amlodipine, Nifedipine)"
            ),
            selfCare = listOf(
                "Monitor blood pressure in a calm state at home twice daily and log it.",
                "Avoid smoking, second-hand smoke, and limit alcohol consumption (less than 1 drink/day for women, 2 for men).",
                "Practice stress management techniques like deep-breathing exercises."
            ),
            warningSigns = listOf(
                "Hypertensive Crisis: Blood pressure exceeding 180/120 mmHg accompanied by chest pain, shortness of breath, severe headache, confusion, or changes in vision requires immediate emergency care (ER)."
            ),
            evidenceReference = "Based on ACC/AHA Hypertension Guidelines (2017) and JNC-8 Joint National Committee recommendations."
        ),

        MedicalCondition(
            id = "asthma",
            name = "Bronchial Asthma",
            category = "Respiratory",
            shortDescription = "A chronic disease of the lungs causing airways to inflame, narrow, and swell, leading to difficulty breathing.",
            fullDescription = "Asthma is a heterogeneous disease, usually characterized by chronic airway inflammation. It is defined by the history of respiratory symptoms such as wheeze, shortness of breath, chest tightness, and cough that vary over time and in intensity, together with variable expiratory airflow limitation.",
            symptoms = listOf(
                "Shortness of breath (especially during exertion or at night)",
                "Wheezing (a high-pitched whistling sound when exhaling)",
                "Chest tightness, discomfort, or mild pressure",
                "Coughing episodes (frequently worse at night or early morning)",
                "Symptom triggers include pollen, cold air, smoke, exercise, and stress"
            ),
            firstLineTreatments = listOf(
                "Identification and avoidance of trigger substances (dust mites, animal dander, tobacco smoke, high pollen).",
                "Inhaled Corticosteroids (ICS) as first-line long-term controller therapy (e.g., Fluticasone, Budesonide) to suppress airway inflammation.",
                "Short-Acting Beta-Agonists (SABA) (e.g., Albuterol) used strictly on-demand as a quick-relief rescue inhaler."
            ),
            prescriptionTreatments = listOf(
                "Combination ICS-LABA controller inhalers (e.g., Budesonide/Formoterol, Fluticasone/Salmeterol).",
                "Leukotriene Receptor Antagonists (LTRA) (e.g., Montelukast) as secondary oral maintenance therapy.",
                "Systemic oral corticosteroids (e.g., Prednisone) for severe acute exacerbations under strict physician monitoring."
            ),
            selfCare = listOf(
                "Formulate and closely follow a personalized " + "Asthma Action Plan" + " with a healthcare specialist.",
                "Utilize a peak flow meter daily to assess lung capacity and predict onset of flare-ups.",
                "Wash target bedding items weekly in hot water to limit dust mite contact."
            ),
            warningSigns = listOf(
                "Severe Asthma Exacerbation: Inability to speak complete sentences in a single breath, chest retractions (skin sucking in around ribs), retractions in throat, bluish fingernails or lips, or lack of response to rescue inhalers requires emergency room (ER) transport."
            ),
            evidenceReference = "Based on Global Initiative for Asthma (GINA) Clinical Guidelines (2024 update)."
        ),

        MedicalCondition(
            id = "gerd",
            name = "GERD (Acid Reflux Disease)",
            category = "Gastrointestinal",
            shortDescription = "A classic chronic condition where stomach acid frequently backflows into the esophagus, irritating its lining.",
            fullDescription = "Gastroesophageal Reflux Disease (GERD) is defined clinically as symptoms or mucosal damage produced by the abnormal reflux of gastric contents. Prolonged acid exposure can lead to complications such as esophagitis, strictures, or Barrett's esophagus, which is a precursor to esophageal cancer.",
            symptoms = listOf(
                "Heartburn: A burning, warm sensation in the chest, typically occurring after eating or aggravated by lying down.",
                "Acid Regurgitation: Unprovoked backup of sour, acidic, or partially digested fluid into the throat.",
                "Dysphagia: Difficulty or chest discomfort when swallowing food.",
                "Chronic dry cough or persistent sore throat.",
                "Feeling of a 'lump' in the back of the throat (globus sensation)."
            ),
            firstLineTreatments = listOf(
                "Dietary modifications: Avoid known acid-triggering items (caffeine, high-fat fried foods, citrus fruits, tomatoes, chocolate, peppermint).",
                "Antacids (e.g., Calcium Carbonate) for immediate, temporary localized relief.",
                "H2 Receptor Antagonists (H2RAs) (e.g., Famotidine) for mild, intermittent symptomatic acid reduction."
            ),
            prescriptionTreatments = listOf(
                "Proton Pump Inhibitors (PPIs) (e.g., Omeprazole, Esomeprazole, Pantoprazole) taken daily 30-60 minutes before the first meal of the day.",
                "Prokinetic agents (e.g., Metoclopramide) to increase gastric emptying (used rarely due to safety profiles)."
            ),
            selfCare = listOf(
                "Refrain from assuming a lying down or reclining position for at least 3 hours following any meal.",
                "Elevate the head of your bed by 6 to 8 inches using mattress wedge pillows (standard pillows only bend the neck and do not work).",
                "Maintain a healthy stomach weight; wear loose clothing to avoid excessive abdominal pressure."
            ),
            warningSigns = listOf(
                "Red Flags: Swallowing food feels stuck in the chest, passing black tarry stools (melena), vomiting blood or coffee-ground material, or unexplained weight loss requires urgent endoscopic evaluation."
            ),
            evidenceReference = "Based on American College of Gastroenterology (ACG) Guidelines for GERD Diagnosis and Management."
        ),

        MedicalCondition(
            id = "type2_diabetes",
            name = "Type 2 Diabetes Mellitus",
            category = "Endocrine",
            shortDescription = "A chronic metabolic disorder characterized by high blood sugar, insulin resistance, and relative lack of insulin.",
            fullDescription = "Type 2 Diabetes occurs when the body's cells become resistant to insulin, the hormone that regulates sugar absorption. Over time, the pancreas cannot produce enough insulin. Poorly controlled, it damages blood vessels throughout the body, causing cardiovascular disease, neuropathy, diabetic retinopathy, and kidney failure.",
            symptoms = listOf(
                "Polydipsia (increased, unquenchable thirst)",
                "Polyuria (frequent urination, particularly during night hours)",
                "Polyphagia (pronounced hunger even after regular meals)",
                "Unintentional weight loss and general, pervasive fatigue",
                "Slow-healing cuts, scrapes, or mild infections"
            ),
            firstLineTreatments = listOf(
                "Aggressive lifestyle counseling: Weight loss of 5 - 10% of total body mass using a structured exercise program.",
                "Medical Nutrition Therapy: Switch to low glycemic index, carbohydrate-consistent, fiber-rich diets.",
                "Metformin: The consensus first-line pharmacological treatment, which lowers hepatic glucose production and increases insulin sensitivity."
            ),
            prescriptionTreatments = listOf(
                "SGLT2 inhibitors (e.g., Empagliflozin, Dapagliflozin) - provides excellent cardiorenal benefits.",
                "GLP-1 receptor agonists (e.g., Semaglutide, Liraglutide) - provides high glycemic control and weight reduction.",
                "DPP-4 inhibitors, Sulfonylureas, or basal/bolus insulin regimens for advanced or uncontrolled blood glucose levels."
            ),
            selfCare = listOf(
                "Execute rigorous daily foot care checks. Inspect for minor cuts, blisters, color changes, or cracks.",
                "Perform regular blood glucose monitoring or use a continuous glucose monitor (CGM).",
                "Schedule precise annual dilated eye examinations to evaluate for diabetic retinopathy."
            ),
            warningSigns = listOf(
                "Metabolic Emergencies: Extreme confusion, fruity breath odor, rapid breathing, severe vomiting (Diabetic Ketoacidosis/DKA), or extreme dehydration with blood sugar >600 mg/dL (HHNS) requires emergency room intervention."
            ),
            evidenceReference = "Based on American Diabetes Association (ADA) Standards of Care in Diabetes (2024 annual update)."
        ),

        MedicalCondition(
            id = "migraine",
            name = "Migraine Headache",
            category = "Neurology",
            shortDescription = "A neurological disease characterized by recurrent moderate-to-severe throbbing, pulsating headaches.",
            fullDescription = "Migraines are complex neurovascular events. They typically afflict one side of the head, last between 4 and 72 hours, and are accompanied by sensory disturbances. Many patients experience a warning premonition phase (aura) consisting of visual, sensory, or speech changes before the headache develops.",
            symptoms = listOf(
                "Unilateral (one-sided) throbbing or pulsating head pain",
                "Nausea, occasional vomiting, and stomach discomfort",
                "Severe photophobia (sensitivity to light) and phonophobia (sensitivity to sound)",
                "Visual Aura: Flashing lights, zig-zag lines, blind spots (scotomas) starting 20-60 minutes before the headache.",
                "Symptom triggers include sleep deprivation, caffeine withdrawal, stress, or specific foods."
            ),
            firstLineTreatments = listOf(
                "Resting in a quiet, dark, well-ventilated room with a cool cloth on the forehead.",
                "Nonsteroidal anti-inflammatory drugs (NSAIDs) such as Ibuprofen, Naproxen, or combination therapy (Acetaminophen/Aspirin/Caffeine) for mild to moderate attacks.",
                "Pre-emptive trigger avoidance (consistent meal and sleep patterns)."
            ),
            prescriptionTreatments = listOf(
                "Triptans (e.g., Sumatriptan, Rizatriptan) taken immediately at the early onset of pain (abortive treatment).",
                "CGRP antagonists (e.g., Rimegepant, Ubrogepant) for abortive or preventative therapy.",
                "Preventative daily medications for frequent attacks (e.g., Topiramate, Propranolol, Amitriptyline, or monthly CGRP monoclonal antibody injections)."
            ),
            selfCare = listOf(
                "Maintain a meticulous headache log documenting triggers, medication response, and frequency.",
                "Engage in progressive muscle relaxation, meditation, or cognitive behavioral therapy (CBT) for stress relief.",
                "Prioritize extreme consistency in sleep schedules, taking care to wake and sleep at the same hours daily."
            ),
            warningSigns = listOf(
                "Red Flags (SNOOP): 'Thunderclap' headache peaking in seconds, headache with fever, stiff neck, confusion, new cognitive deficits, or a headache that feels completely different from your typical patterns requires immediate ER transport."
            ),
            evidenceReference = "Based on American Headache Society (AHS) and American Academy of Neurology (AAN) consensus statements."
        ),

        MedicalCondition(
            id = "gout",
            name = "Gouty Arthritis (Gout)",
            category = "Musculoskeletal",
            shortDescription = "A painful form of inflammatory arthritis caused by the accumulation of uric acid crystals in a joint.",
            fullDescription = "Gout is characterized by deposition of monosodium urate crystals in synovial fluid, ligaments, and cartilage. It occurs when serum uric acid levels exceed solubility limits. Attacks are sudden, usually affecting a single joint, with the first metatarsophalangeal (MTP) joint (big toe) being the classic initial target.",
            symptoms = listOf(
                "Sudden, excruciating, throbbing joint pain (often starting in the middle of the night)",
                "Pronounced swelling, redness, warmth, and severe tenderness in the joint (even light bedsheets cause severe pain)",
                "Standard joint locations: Big toe (podagra), knees, ankles, or wrists",
                "Limited range of motion in the affected joint during attacks"
            ),
            firstLineTreatments = listOf(
                "Immediate non-pharmacological ice pack application to the inflamed joint to reduce local temperature.",
                "Oral NSAIDs (e.g., High-dose Naproxen, Indomethacin) initiated immediately at onset.",
                "Low-dose Colchicine taken within 36 hours of acute attack initiation.",
                "A low-purine diet focusing on avoiding red meats, organ meats, shellfish, beer, high-fructose corn syrup."
            ),
            prescriptionTreatments = listOf(
                "Oral or intra-articular Corticosteroids (e.g., Methylprednisolone, Prednisone) for patients unable to take NSAIDs or Colchicine.",
                "Urate-Lowering Therapy (ULT) (e.g., Allopurinol, Febuxostat) for patients with recurrent gout flares, to be taken consistently to maintain uric acid levels below 6.0 mg/dL."
            ),
            selfCare = listOf(
                "Hydrate extensively (consuming 2-3 liters of water daily) to help kidneys excrete uric acid.",
                "Refuse alcohol, and avoid consuming heavy sugary sodas.",
                "Elevate the affected limb during acute painful flares to minimize local blood pooling."
            ),
            warningSigns = listOf(
                "Complication: A hot, red, swollen joint accompanied by high body fever, chills, or extreme systemic malaise may indicate Septic Arthritis (joint infection) and requires urgent medical aspiration immediately."
            ),
            evidenceReference = "Based on American College of Rheumatology (ACR) Guidelines for the Management of Gout."
        ),

        MedicalCondition(
            id = "uti",
            name = "Urinary Tract Infection (UTI)",
            category = "Infectious Diseases",
            shortDescription = "An infection in any part of the urinary system, usually caused by bacteria, most commonly E. coli.",
            fullDescription = "UTIs are highly prevalent bacterial infections, particularly in women. They are divided into uncomplicated lower urinary tract infections (Cystitis, localized in the bladder) and complicated infections which can spread to the kidneys (Pyelonephritis), leading to systemic infection.",
            symptoms = listOf(
                "Dysuria: Strong, burning, painful sensation when urinating",
                "Urinary Urgency: Sudden, forceful need to urinate immediately",
                "Urinary Frequency: Urinating often but passing only tiny amounts",
                "Cloudy, dark, turbid, or unusually strong-smelling urine",
                "Hematuria: Visible pink, red, or smoky blood in the urine",
                "Pelvic pressure or discomfort in the lower center of the abdomen"
            ),
            firstLineTreatments = listOf(
                "Oral Antibiotic course (the only curative treatment to clear the bacterial infection).",
                "Phenazopyridine (OTC) used for up to two days to numb the urinary tract and reduce pelvic burning pain (note: turns urine bright orange).",
                "Increased fluid intake (water) to flush bacteria mechanically out of the bladder."
            ),
            prescriptionTreatments = listOf(
                "Nitrofurantoin (Macrobid) typically taken twice daily for 5 days.",
                "Trimethoprim-Sulfamethoxazole (Bactrim) for 3 days (if local resistance rates are low).",
                "Fosfomycin as a single, highly convenient powdered oral dose."
            ),
            selfCare = listOf(
                "Drink abundant amounts of pure water during active treatment.",
                "Always wipe from front to back after using the restroom to prevent rectal bacteria from approaching the urethra.",
                "Urinate immediately after sexual intercourse to clear any dislodged bacteria."
            ),
            warningSigns = listOf(
                "Pyelonephritis (Kidney Infection): Development of back or flank pain, high fever (>100.4°F / 38°C), shaking chills, nausea, or vomiting requires urgent medical care, as it can cause permanent kidney scarring or sepsis."
            ),
            evidenceReference = "Based on Infectious Diseases Society of America (IDSA) Uncomplicated Cystitis Treatment Guidelines."
        ),

        MedicalCondition(
            id = "eczema",
            name = "Atopic Dermatitis (Eczema)",
            category = "Dermatology",
            shortDescription = "A chronic, inflammatory skin condition that causes itchy, red, swollen, and cracked skin.",
            fullDescription = "Atopic Dermatitis is a common, chronic, relapsing skin disease that often begins in childhood and is associated with a barrier dysfunction in the skin, allowing moisture to escape and allergens or microbes to enter. It is strongly linked with other atopic diseases like asthma and allergic rhinitis.",
            symptoms = listOf(
                "Pruritus: Dry, sensitive, extremely intense itching skin (often worse at night)",
                "Red to brownish-gray patches, especially on hands, feet, ankles, wrists, neck, inner elbows, knees",
                "Small, raised bumps that may leak fluid and crust over when scratched",
                "Thickened, cracked, scaly, or leathery skin from chronic rubbing (lichenification)",
                "Symptom triggers include wool fabrics, harsh soaps, dust mites, dry winter air"
            ),
            firstLineTreatments = listOf(
                "Consistent, liberal skin barrier repair. Apply thick ointment or cream-based moisturizers (e.g., Petrolatum, Ceramide creams) within 3 minutes of bathing.",
                "Switching to hypoallergenic, fragrance-free, dye-free body washes and laundry detergents.",
                "Warm, short baths (5-10 minutes) rather than hot showers which deplete natural skin lipids."
            ),
            prescriptionTreatments = listOf(
                "Topical Corticosteroids (e.g., Hydrocortisone, Triamcinolone, Clobetasol) used strictly during flare-ups.",
                "Topical Calcineurin Inhibitors (e.g., Tacrolimus, Pimecrolimus) for sensitive facial or skin fold regions.",
                "Systemic immunosuppressants or biological agents (e.g., Dupilumab) for severe, resistant pediatric and adult eczema cases."
            ),
            selfCare = listOf(
                "Trim fingernails very short or wear soft cotton gloves at night to prevent sleep scratching lesions.",
                "Deploy cool-mist humidifiers in bedrooms to maintain positive humidity levels.",
                "Pat skin dry gently with a towel after bathing; never rub or drag the skin."
            ),
            warningSigns = listOf(
                "Secondary Infection: Any weeping yellow crusts, oozing blisters, severe redness spreading around eczema spots, or painful sores suggests a bacterial (Staph) or viral (eczema herpeticum) infection. This requires immediate clinical antibiotics or antiviral therapy."
            ),
            evidenceReference = "Based on American Academy of Dermatology (AAD) guidelines of care for the management of Atopic Dermatitis."
        ),

        // --- EMERGENCY CONDITIONS ---
        MedicalCondition(
            id = "heart_attack",
            name = "Myocardial Infarction (Heart Attack)",
            category = "EMERGENCY CARE",
            shortDescription = "A life-threatening medical emergency where blood flow to a part of the heart muscle is blocked.",
            fullDescription = "Myocardial Infarction occurs when one or more coronary arteries are occluded, leading to ischemic necrosis of the myocardium. Time is muscle: the sooner blood flow is restored (via catheterization or thrombolytics), the less permanent damage occur. Call emergency services immediately if suspected.",
            symptoms = listOf(
                "Chest pain, pressure, squeezing, fullness, or tightness, usually in the center or left side of the chest.",
                "Pain radiating to the left shoulder, left arm, neck, jaw, upper back, or stomach area.",
                "Shortness of breath, with or without chest discomfort.",
                "Sudden cold sweat, severe lightheadedness, dizziness, or syncope (passing out).",
                "Atypical presentation (common in women and diabetics): Nausea, vomiting, severe indigestion, or profound unexplained fatigue."
            ),
            firstLineTreatments = listOf(
                "Call Emergency Services (911 / Local emergency) immediately! Do not attempt to drive yourself to the clinic.",
                "Chew and swallow a standard adult non-enteric coated Aspirin (325 mg) or 2-4 baby Aspirins immediately if no contraindications exist."
            ),
            prescriptionTreatments = listOf(
                "Intravenous Thrombolytic therapy to dissolve clots (if cath lab is unavailable).",
                "Emergency Percutaneous Coronary Intervention (PCI) with cardiac angioplasty and stenting in a cardiac catheterization suite."
            ),
            selfCare = listOf(
                "Sit down, relax, and remain completely still in a calm environment while awaiting paramedic response.",
                "Do not engage in physical exertion, which places high oxygen demands on the compromised heart muscle."
            ),
            warningSigns = listOf(
                "CRITICAL WARNING: This is an active medical emergency. Do NOT wait to see if symptoms resolve. Every second of delay increases muscle damage and the risk of sudden cardiac arrest."
            ),
            evidenceReference = "Based on AHA/ACC STEMI Guidelines (2021 update)."
        ),

        MedicalCondition(
            id = "anaphylaxis",
            name = "Anaphylactoid Reaction / Anaphylaxis",
            category = "EMERGENCY CARE",
            shortDescription = "A severe, potentially life-threatening systemic allergic reaction that occurs within seconds or minutes of exposure.",
            fullDescription = "Anaphylaxis is an aggressive mediator-released IgE-dependent cascade causing vasodilation, bronchial smooth muscle constriction, and laryngeal edema. It can progress to circulatory shock, airway closure, and death in minutes. Immediate Epinephrine injection is the singular life-saving intervention.",
            symptoms = listOf(
                "Airway: Swelling of the lips, tongue, or throat causing difficulty swallowing or breathing",
                "Lungs: Intense wheezing, coughing, stridor (high-pitched breathing), or deep chest tightness",
                "Skin: Rapidly spreading hives (urticaria), intense itching, warm flushing, or angioedema (tissue swelling)",
                "Circulation: Rapid drop in blood pressure, dizziness, weak thready pulse, or loss of consciousness",
                "Digestive: Severe abdominal cramps, persistent nausea, vomiting, or diarrhea",
                "Triggers: Peanut allergen, bee/wasp stings, penicillin, latex, or shellfish exposure"
            ),
            firstLineTreatments = listOf(
                "Inject Epinephrine (EpiPen / Auvi-Q) intramuscularly into the outer thigh immediately upon suspecting reaction.",
                "Call Emergency Services / transport to ER instantly! An emergency evaluation is mandatory even if symptoms resolve.",
                "Lie down flat with legs elevated to support blood pressure if feeling dizzy or faint."
            ),
            prescriptionTreatments = listOf(
                "Oxygen administration.",
                "Intravenous fluids to expand circular volume.",
                "Inhaled Albuterol for bronchospasms.",
                "IV Antihistamines and Corticosteroids to prevent a biphasic rebound recurrence."
            ),
            selfCare = listOf(
                "Always carry two functional Auto-Injectors of Epinephrine at all times.",
                "Wear an identifiable medical alert bracelet clearly stating known anaphylactic triggers.",
                "Educate close family, friends, and co-workers in the correct alignment and delivery of Epinephrine injectors."
            ),
            warningSigns = listOf(
                "CRITICAL WARNING: Anaphylaxis is extremely dynamic and can culminate in death in minutes due to complete airway obstruction. Epinephrine is the primary, indispensable treatment. Antihistamines are supportive only and cannot halt airway closure!"
            ),
            evidenceReference = "Based on Joint Task Force on Practice Parameters (AAAAI/ACAAI) Anaphylaxis Practice Guidelines (2023)."
        )
    )

    fun getConditionById(id: String): MedicalCondition? {
        return conditions.find { it.id == id }
    }
}
