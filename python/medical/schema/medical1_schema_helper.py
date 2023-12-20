# ATTENTION!
# This file is generated by Schema automatically, it will be refreshed after schema has been committed
# PLEASE DO NOT MODIFY THIS FILE!!!
#

class Medical1:
	def __init__(self):
		self.BodyPart = self.BodyPart()
		self.Disease = self.Disease()
		self.Drug = self.Drug()
		self.HospitalDepartment = self.HospitalDepartment()
		self.Indicator = self.Indicator()
		self.Symptom = self.Symptom()

	class BodyPart:
		__typename__ = "Medical1.BodyPart"
		description = "description"
		id = "id"
		name = "name"
		stdId = "stdId"
		alias = "alias"

		def __init__(self):
			pass

	class Disease:
		__typename__ = "Medical1.Disease"
		description = "description"
		id = "id"
		name = "name"
		diseaseSite = "diseaseSite"
		complication = "complication"
		applicableDrug = "applicableDrug"
		commonSymptom = "commonSymptom"
		department = "department"

		def __init__(self):
			pass

	class Drug:
		__typename__ = "Medical1.Drug"
		description = "description"
		id = "id"
		name = "name"

		def __init__(self):
			pass

	class HospitalDepartment:
		__typename__ = "Medical1.HospitalDepartment"
		description = "description"
		id = "id"
		name = "name"
		alias = "alias"
		stdId = "stdId"

		def __init__(self):
			pass

	class Indicator:
		__typename__ = "Medical1.Indicator"
		description = "description"
		id = "id"
		name = "name"

		def __init__(self):
			pass

	class Symptom:
		__typename__ = "Medical1.Symptom"
		description = "description"
		id = "id"
		name = "name"

		def __init__(self):
			pass

