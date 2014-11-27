/*
 * Copyright (c) 2013, 2014 Institute for Internet Security - if(is)
 *
 * This file is part of Hermes Malware Analysis System.
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they
 * will be approved by the European Commission - subsequent
 * versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the
 * Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl 5
 *
 * Unless required by applicable law or agreed to in
 * writing, software distributed under the Licence is
 * distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.
 * See the Licence for the specific language governing
 * permissions and limitations under the Licence.
 */

package net.ifis.ites.hermes.management

import grails.transaction.Transactional
import net.ifis.ites.hermes.util.Constants as C
import org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib

/**
 * DataTable service to manage the datatable plugin.
 * 
 * @author Andreas Sekulski
 */
class DataTableService {
    
    /**
     * Injected TagLib Service
     **/
    def g = new ApplicationTagLib()
    
    /**
     * Get the default initialization data from an data table.
     * 
     * @return an Map with all default initialization data.
     **/
    public Map getDefaultData() {
        return [
            "bJQueryUI": true,
            "bDestroy": true,
            "bAutoWidth": false,
            "oLanguage": [
                "sEmptyTable": g.message(code: 'js.data.table.empty.table'),
                "sInfo": g.message(code: 'js.data.table.info'),
                "sInfoEmpty": g.message(code: 'js.data.table.info.empty'),
                "sSearch": g.message(code: 'js.data.table.search'),
                "sLengthMenu": g.message(code: 'js.data.table.length.menu'),
                "sInfoFiltered": g.message(code: 'js.data.table.info.filtered'),
                "sZeroRecords": g.message(code: 'js.data.table.empty.records'),
                "oPaginate": [
                    "sNext": g.message(code: 'js.data.table.pagination.next'),
                    "sPrevious": g.message(code: 'js.data.table.pagination.back'),
                    "sLast": g.message(code: 'js.data.table.pagination.last'),
                    "sFirst": g.message(code: 'js.data.table.pagination.first')
                ],
                "sProcessing": g.message(code: 'js.data.table.processing'),
                "sLoadingRecords": g.message(code: 'js.data.table.loading')
            ]
        ]
    }
}