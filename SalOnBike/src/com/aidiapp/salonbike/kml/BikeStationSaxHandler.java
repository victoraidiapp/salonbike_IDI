package com.aidiapp.salonbike.kml;

import java.util.jar.Attributes;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
/**
 * Esta clase sirve para manejar el XML leido con SAX y convertir los nodos XML en objetos de la colección 
 * bikestationDataSet
 * @author escueladecienciasinformaticas
 *
 */
public class BikeStationSaxHandler extends DefaultHandler{ 

	 // =========================================================== 
	 // Campos 
	 // =========================================================== 

	 private boolean in_kmltag = false; 
	 private boolean in_placemarktag = false; 
	 private boolean in_nametag = false;
	 private boolean in_descriptiontag = false;
	 private boolean in_geometrycollectiontag = false;
	 private boolean in_linestringtag = false;
	 private boolean in_pointtag = false;
	 private boolean in_coordinatestag = false;

	 private StringBuffer buffer;

	 private BikeStationDataSet bikestationDataSet = new BikeStationDataSet(); 

	 // =========================================================== 
	 // Getter & Setter 
	 // =========================================================== 

	 public BikeStationDataSet getParsedData() {
	      bikestationDataSet.getCurrentPlacemark().setCoordinates(buffer.toString().trim());
	      return this.bikestationDataSet; 
	 } 

	 // =========================================================== 
	 // Methods 
	 // =========================================================== 
	 @Override 
	 public void startDocument() throws SAXException { 
	      this.bikestationDataSet = new BikeStationDataSet(); 
	      Log.d("SAXHANDLER", "Empieza el documento ");
	 } 

	 @Override 
	 public void endDocument() throws SAXException { 
	      // Nothing to do
	 } 

	
	 @Override
	public void startElement(String namespaceURI, String localName, String qName,
			org.xml.sax.Attributes attributes) throws SAXException {
		 Log.d("SAXHANDLER", "Queremos abrir la etiqueta "+localName);
	      if (localName.equals("kml")) { 
	           this.in_kmltag = true;
	      } else if (localName.equals("Placemark")) { 
	           this.in_placemarktag = true; 
	           bikestationDataSet.setCurrentPlacemark(new BikeStation());
	           
	      } else if (localName.equals("name")) { 
	           this.in_nametag = true;
	      } else if (localName.equals("description")) { 
	          this.in_descriptiontag = true;
	      } else if (localName.equals("GeometryCollection")) { 
	          this.in_geometrycollectiontag = true;
	      } else if (localName.equals("LineString")) { 
	          this.in_linestringtag = true;              
	      } else if (localName.equals("Point")) { 
	          this.in_pointtag = true;          
	      } else if (localName.equals("coordinates")) {
	          buffer = new StringBuffer();
	          this.in_coordinatestag = true;                        
	      }
		super.startElement(namespaceURI, localName, qName, attributes);
	}
	 

	 /** Gets be called on closing tags like: 
	  * </tag> */ 
	 @Override 
	 public void endElement(String namespaceURI, String localName, String qName) 
	           throws SAXException { 
		 Log.d("SAXHANDLER", "Queremos cerrar la etiqueta "+localName);
	       if (localName.equals("kml")) {
	           this.in_kmltag = false; 
	       } else if (localName.equals("Placemark")) { 
	           this.in_placemarktag = false;

	        bikestationDataSet.addCurrentPlacemark();

	       } else if (localName.equals("name")) { 
	           this.in_nametag = false;           
	       } else if (localName.equals("description")) { 
	           this.in_descriptiontag = false;
	       } else if (localName.equals("GeometryCollection")) { 
	           this.in_geometrycollectiontag = false;
	       } else if (localName.equals("LineString")) { 
	           this.in_linestringtag = false;              
	       } else if (localName.equals("Point")) { 
	           this.in_pointtag = false;          
	       } else if (localName.equals("coordinates")) { 
	           this.in_coordinatestag = false;
	       }
	 } 

	 /** Gets be called on the following structure: 
	  * <tag>characters</tag> */ 
	 @Override 
	public void characters(char ch[], int start, int length) { 
		 
	    if(this.in_nametag){ 
	        if (bikestationDataSet.getCurrentPlacemark()==null) bikestationDataSet.setCurrentPlacemark(new BikeStation());
	        bikestationDataSet.getCurrentPlacemark().setTitle(new String(ch, start, length));            
	    } else 
	    if(this.in_descriptiontag){ 
	        if (bikestationDataSet.getCurrentPlacemark()==null) bikestationDataSet.setCurrentPlacemark(new BikeStation());
	        bikestationDataSet.getCurrentPlacemark().setDescription(new String(ch, start, length));          
	    } else
	    if(this.in_coordinatestag){        
	        if (bikestationDataSet.getCurrentPlacemark()==null) bikestationDataSet.setCurrentPlacemark(new BikeStation());
	        //navigationDataSet.getCurrentPlacemark().setCoordinates(new String(ch, start, length));
	        bikestationDataSet.getCurrentPlacemark().setPosition(BikeStation.coordinatesToPosition(new String(ch,start,length)));
	        buffer.append(ch, start, length);
	    }
	} 
	}
