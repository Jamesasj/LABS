(for /F %%a in (hosts.txt) do ( 
	for /F "tokens=2,3,4 delims=,():" %%I in ('ping %%a ^| find "Pacotes:"') do (
		echo %date%;%%a;%%I;%%J;%%K >> saida.csv
	)
))