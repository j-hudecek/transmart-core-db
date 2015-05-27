package org.transmartproject.db.dataquery.highdim.tworegion

import groovy.transform.EqualsAndHashCode
import org.transmartproject.core.dataquery.highdim.tworegion.JunctionEvent

/**
 * Created by j.hudecek on 4-12-2014.
 */
@EqualsAndHashCode()
class DeTwoRegionJunctionEvent implements Serializable, JunctionEvent {

    Integer readsSpan
    Integer readsJunction
    Integer pairsSpan
    Integer pairsJunction
    Integer pairsEnd
    Integer pairsCounter
    Double baseFreq
    DeTwoRegionEvent event
    DeTwoRegionJunction junction

    static constraints = {
        readsSpan(nullable: true)
        readsJunction(nullable: true)
        pairsSpan(nullable: true)
        pairsJunction(nullable: true)
        pairsEnd(nullable: true)
        pairsCounter(nullable: true)
        baseFreq(nullable: true)
    }


    static mapping = {
        table schema: 'deapp', name: 'de_two_region_junction_event'
        id column: 'two_region_junction_event_id'

        readsSpan column: 'reads_span'
        readsJunction column: 'reads_junction'
        pairsSpan column: 'pairs_span'
        pairsJunction column: 'pairs_junction'
        pairsEnd column: 'pairs_end'
        pairsCounter column: 'reads_counter'
        baseFreq column: 'base_freq'

        /* references */
        event column: 'event_id'
        event fetch: 'join'
        junction fetch: 'join'

        version false
    }

    Map toNonNullMap() {
        def exportedCols = ['readsSpan',
                            'readsJunction',
                            'pairsSpan',
                            'pairsJunction',
                            'pairsEnd',
                            'pairsCounter',
                            'baseFreq']
        def ret = [:]
        exportedCols.each({ ret.put(it,this[it]) })
        ret.put('event.id', event.id)
        ret.put('event.cgaType', event.cgaType)
        ret.put('event.soapClass', event.soapClass)
        ret.put('event.genes', event.eventGenes.collect({it.geneId}))
        ret.findAll {it.value != null}
    }
}

