package Servlet;

import PettyCash.PettyCashes;
import PettyCash.PettyCashController;
import PettyCash.PettyCash;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.ArrayList;

@WebServlet("/jsonservlet")
public class JSONServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    PettyCashController PCC = new PettyCashController();

    // This will store all received pettycashes
    ArrayList<PettyCash> pettycashes = new ArrayList<PettyCash>() {
    };
    PettyCashes pcs = new PettyCashes();

    /**
     * *************************************************
     * URL: /jsonservlet doPost(): receives JSON data, parse it, map it and send
     * back as JSON **************************************************
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if (br != null) {
            json = br.readLine();
            System.out.println(json);
        }

        // initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        // Convert received JSON to PettyCash
        PettyCash pettycash = mapper.readValue(json, PettyCash.class);
        
        // Identify Action and call method
        String action = pettycash.getAction();
        
        if (action.equals("requestPettyCash")) {
            PCC.addPettyCash(pettycash.getEmp_id(), pettycash.getDescription(), pettycash.getAmount());
        } else if (action.equals("readPettyCash")) {
            PCC.readPettyCash();
        } else if (action.equals("getEditPettyCash")||action.equals("getPettyCashDetail")) {
            PCC.getPettyCashDetail(pettycash.getCode());
        } else if (action.equals("editPettyCash")) {
            PCC.editPettyCash(pettycash.getCode(), pettycash.getEmp_id(), pettycash.getDescription(), pettycash.getAmount());
        } else if (action.equals("deletePettyCash")) {
            PCC.deletePettyCash(pettycash.getCode());
        } else if (action.equals("cancelPettyCash")) {
            PCC.cancelPettyCash(pettycash.getCode());
        } else if (action.equals("approvePettyCash")) {
            PCC.approvePettyCash(pettycash.getCode());
        } else if (action.equals("notApprovePettyCash")) {
            PCC.notApprovePettyCash(pettycash.getCode());
        }
        
        // Set response type to JSON
        response.setContentType("application/json");

        // Add pettycash to List<PettyCash>
        pettycashes = PCC.pettycashes;

        // Send List<PettyCash> as JSON to client
        mapper.writeValue(response.getOutputStream(), pettycashes);
    }
}
