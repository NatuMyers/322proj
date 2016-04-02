// $Id: CSVDocument.java,v 1.0 2012/10/04 13:57:18 dalamb Exp $
import java.awt.Container;
import java.io.*;
//import java.util.*;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
// Import only those classes from edfmwk that are essential, for documentation purposes
import ca.queensu.cs.dal.edfmwk.doc.AbstractDocument;
import ca.queensu.cs.dal.edfmwk.doc.DocumentType;
import ca.queensu.cs.dal.edfmwk.doc.DocumentEvent;
import ca.queensu.cs.dal.edfmwk.doc.DocumentException;
import ca.queensu.cs.dal.edfmwk.doc.DocumentListener;

/**
 * Implementation of a text document, which is (indirectly) defined in
 * terms of a Swing {@link javax.swing.Document}.
 *<p>
 * Copyright 2010 David Alex Lamb.
 * See the <a href="../doc-files/copyright.html">copyright notice</a> for details.
 */
public class CSVDocument
    extends AbstractDocument
    implements javax.swing.event.DocumentListener
{
    private static int numRows = 20;
    private static int numColumns = 80;
    private CSVContents contents;
    public String[][] rows;

    /**
     * Constructs a document representation.
     * @param type The type of the document.
     */
    public CSVDocument(DocumentType type) {
		super(type);
		contents = new CSVContents();
		contents.addDocumentListener(this);
	    JTextArea jta = new JTextArea(numRows, numColumns);
	    jta.setDocument(contents);
	    window = new JScrollPane(jta);
    } // end CSVDocument

    // Text document change listeners: all invoke the framework's own document
    // change listeners.

    /**
     * Gives notification that an attribute or set of attributes changed.
     */
    public void changedUpdate(javax.swing.event.DocumentEvent e) {
	setChanged();
    } // end changedUpdate

    /**
     * Gives notification that there was an insert into the document.
     */
    public void insertUpdate(javax.swing.event.DocumentEvent e) {
	setChanged();
    } // end insertUpdate

    /**
     * Gives notification that a portion of the document has been removed.
     */
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
	setChanged();
    } // end removeUpdate

    /**
     * Saves the entire document.  After this operation completes
     * successfully, {@link #isChanged} returns <b>false</b>
     * @param out Where to write the document.
     * @throws IOException if any I/O errors occur, in which case it will have
     * closed the stream; isChanged() is unchanged.
     */
    public void save(OutputStream out) throws IOException {
	CSVHandler.writeToCSVFile(rows, out);
	setChanged(false);
    } // save

    /**
     * Gets an input stream from which the document contents can be read as a
     *  stream of bytes.  This is required when running in a sandbox, where
     *  {@link javax.jnlp.FileSaveService#saveAsFileDialog} does not provide a
     *  means of supplying an output stream to which to write the internal
     *  representation. Document managers should avoid using this method
     *    wherever possible, preferring {@link #save} instead.
     * @throws IOException if such a stream cannot be created.
     */
    public InputStream getContentsStream() throws DocumentException {
	return contents.getContentsStream();
    } // getContentsStream

    /**
     * Reads the entire document, and closes the stream from which it is read.
     * @param in Where to read the document from.
     * @throws IOException if any I/O errors occur, in which case it will have
     * closed the stream.
     */
    public void open(InputStream in)
	throws IOException
    {
	
	String[][] csv = contents.open(in);
	setRows(csv);
	JTable table = null;
		try {
			table = TableModel.makeTable(csv);
		} catch (Exception e) {
// TODO Auto-generated catch block
 		e.printStackTrace();
 			}
	window = new JScrollPane(table);
	setChanged(false);
    } // open

    /**
     * Gets the contents of the text document, for those few methods within
     *    this package that need direct access (such as actions).
     */
    CSVContents getContents() { return contents; }

    public void setRows(String[][] csv){
	    rows = csv;
    }
} // end class CSVDocument

