# Fit-Import

Import CVS files from Withings and Kettler into Google Fit.

## Withings

While Withings has now GoogleFit support this was not always the case. This app allows you to import older data recoded
before Withings started to store in GoogleFit.

## Kettler

# Suported file types:
## Withings

The Withings export format contains a timestamp, weight, fat and an comment.

	"Date","Gewicht (kg)","Fettmasse (kg)","Fettfreier Anteil (kg)","Kommentare"
	"2014-02-13 6:04 Uhr","94.34","26.81","68.43",""
	"2014-02-12 6:09 Uhr","94.89","27.43","67.36",""

##Kettler

The Kettler export format contains start time, end time, and trainings data as integers.

	 Datum     ;Zeit ;Dauer    ;Watt;Puls;u/min;kcal;km;°
         02.02.2014;18:42;00:40:00 ;88  ;118 ;48   ;294 ;6 ;-;
         03.02.2014;18:29;00:40:00 ;88  ;94  ;61   ;294 ;6 ;-;