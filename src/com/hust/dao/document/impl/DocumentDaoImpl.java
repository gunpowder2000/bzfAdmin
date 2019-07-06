package com.hust.dao.document.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.document.DocumentDao;
import com.hust.model.document.Document;

public class DocumentDaoImpl extends BaseDao implements DocumentDao {
	
	/* (non-Javadoc)
	 * @see com.hust.dao.document.impl.DocumentDao#queryDocuments(java.lang.String)
	 */
	public List<Document> queryDocuments(String documentTypeCode){
		List<Document> result=new ArrayList<Document>();
		try {
			con = getConnection(1);
			String sql = "select * from tbl_document t where document_type_code=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, documentTypeCode);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Document document = new Document();
				document.setDocumentContent(rs.getString("document_content"));
				document.setDocumentDigest(rs.getString("document_digest"));
				document.setDocumentId(rs.getLong("document_id"));
				document.setDocumentTitle(rs.getString("document_title"));
				document.setDocumentTypeCode(rs.getString("document_type_code"));
				result.add(document);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.document.impl.DocumentDao#updateDocument(com.hust.model.document.Document)
	 */
	public int updateDocument(Document document){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_document set document_content=? where document_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, document.getDocumentContent());			
			pstm.setLong(2, document.getDocumentId());
			result = pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}

}
