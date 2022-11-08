--- VERIFICA O ESPACO POR TABLESPACE
SELECT 
    d.status "Status",
    d.tablespace_name "Name",
    TO_CHAR(NVL(a.bytes / 1024 / 1024, 0),'99999990D900') "Size (M)",
    TO_CHAR(NVL(NVL(f.bytes, 0), 0)/1024/1024 ,'99999990D900') "Free (MB)",
    TO_CHAR(NVL((NVL(f.bytes, 0)) / a.bytes * 100, 0), '990D00') "Free %"
FROM sys.dba_tablespaces d,
    (select tablespace_name, sum(bytes) bytes from dba_data_files group by tablespace_name) a,
    (select tablespace_name, sum(bytes) bytes from dba_free_space group by tablespace_name) f
WHERE d.tablespace_name = a.tablespace_name(+)
    AND d.tablespace_name = f.tablespace_name(+)
    AND NOT (d.extent_management like 'LOCAL'  AND d.contents like 'TEMPORARY')
order by "Free %";

--- LISTAR OS ARQUIVOS DOS TABLESPACE
select file_name from sys.dba_data_files;

--- ADICIONAR OS ARQUIVO AO TABLESPACE
ALTER TABLESPACE TS_HINNO ADD
DATAFILE '/opt/oracle/product/18c/dbhomeXE/dbs/TS_02.dbf' SIZE 138240K
REUSE AUTOEXTEND
ON NEXT 10240K MAXSIZE 4096M;

ALTER DATABASE DATAFILE '/opt/oracle/product/18c/dbhomeXE/dbs/TS_02.dbf' AUTOEXTEND ON MAXSIZE 4096M;


-- Limpar estatística de Índices
analyze table tb_cic_ciclo delete statistics;

---- Tamanho dos objetos no tablespace
SELECT * FROM
 (select 
    SEGMENT_NAME,
    SEGMENT_TYPE,
    BYTES/1024/1024/1024 GB,
    TABLESPACE_NAME
 from
 dba_segments
 where TABLESPACE_NAME = 'TS_02'
 order by 3 desc ) WHERE
 ROWNUM <= 10;
 
 --- LOB Relacionado ao objeto de banco
SELECT TABLE_NAME,COLUMN_NAME,
SEGMENT_NAME,INDEX_NAME
FROM DBA_LOBS
WHERE SEGMENT_NAME='SYS_LOB0000078685C00011$$';