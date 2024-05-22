package com.example.vti.hospital.service;

import com.example.vti.hospital.models.Record;

import java.util.List;
import java.util.Optional;

public interface RecordService {
    List<Record> getAllRecord();
    List<Record> getRecordByPaging(int pageNumber, int pageSize);
    Record insertRecord( Record newRecord);
    Record deleteRecord(Long id);
    Record updateRecord(Long recordID,Record newRecords);
    Record sreachIdRecord(String recordID);
}
