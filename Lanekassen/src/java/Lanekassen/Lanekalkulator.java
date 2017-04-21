/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lanekassen;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marius
 */
@WebServlet(name = "Lanekalkulator", urlPatterns = {"/Lanekalkulator"})
public class Lanekalkulator extends HttpServlet {

    private boolean enslig;
    private boolean borteboer;
    private int formue;
    private int inntekt;
    private final int maksFormue;
    private final int maksInntekt;
    private String livvstatus;
    private String bostatus;
    
    public Lanekalkulator(){
        this.maksFormue = 392662;
        this.maksInntekt = 172597;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //Henter ut data
            String Livsstatus = request.getParameter("livsstatus");
            String Bostatus = request.getParameter("boStatus");
            String Inntekt = request.getParameter("innTekt");
            String Formue = request.getParameter("formue");
            
            //Oppdaterer felter
            this.livvstatus = Livsstatus;
            this.bostatus = Bostatus;
            
            //Konverterer data
            this.inntekt = Integer.parseInt(Inntekt);
            this.formue = Integer.parseInt(Formue);
            
            //Kjører nødvendige metoder
            sjekkBostatus();
            sjekkLivsstatus();
            String lanebelop = sjekkLanebelop();

            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Lanekalkulator</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(lanebelop);
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void sjekkLivsstatus(){
            if(livvstatus.equalsIgnoreCase("enslig"))
            {
                this.enslig = true;}
            else{
                this.enslig = false;
            }
            System.out.println("sjekkLivsstatus succedeed");
    }
    
    private void sjekkBostatus(){
            if(bostatus.equalsIgnoreCase("borteboer")){
                this.borteboer = true;
            }
            else{
                this.borteboer = false;
            }
            System.out.println("sjekkBostatus succedeed");
    }
            
    private String sjekkLanebelop() {
            //Sjekker mulig lånebeløp
            if(this.borteboer == true && this.enslig == true && this.formue < this.maksFormue && this.inntekt < this.maksInntekt){
                String lanebelop = ("Mulig lånebeløp er: " + 108999 + " kr");
                return lanebelop;
            }
            else if(this.borteboer == true && this.enslig == false && this.formue < this.maksFormue && this.inntekt < this.maksInntekt){
                String lanebelop = ("Mulig lånebeløp er: " + 80000 + " kr" );
                return lanebelop;
            }
            else{
                String lanebelop = ("Du får desverre ikke støtte i år");
                return lanebelop;
            }
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
